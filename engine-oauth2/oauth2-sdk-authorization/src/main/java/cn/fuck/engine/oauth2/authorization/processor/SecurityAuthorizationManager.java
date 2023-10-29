package cn.fuck.engine.oauth2.authorization.processor;

import cn.fuck.engine.oauth2.authorization.definition.HerodotusConfigAttribute;
import cn.fuck.engine.oauth2.authorization.definition.HerodotusRequest;
import cn.fuck.engine.oauth2.authorization.definition.HerodotusRequestMatcher;
import cn.fuck.engine.assistant.core.utils.http.HeaderUtils;
import cn.fuck.engine.rest.core.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * <p>Description: Spring Security 6 授权管理器 </p>
 * <p>
 * Spring Security 6 授权管理
 * 1. 由原来的 AccessDecisionManager 和 AccessDecisionVoter，变更为使用 {@link AuthorizationManager}
 * 2. 原来的 SecurityMetadataSource 已经不再使用。其实想要自己扩展，基本逻辑还是一致。只不过给使用者更大的扩展度和灵活度。
 * 3. 原来的 <code>FilterSecurityInterceptor</code>，已经不再使用。改为使用 {@link org.springframework.security.web.access.intercept.AuthorizationFilter}
 */
public class SecurityAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final Logger log = LoggerFactory.getLogger(SecurityAuthorizationManager.class);

    private final SecurityMetadataSourceStorage securityMetadataSourceStorage;
    private final SecurityMatcherConfigurer securityMatcherConfigurer;

    public SecurityAuthorizationManager(SecurityMetadataSourceStorage securityMetadataSourceStorage, SecurityMatcherConfigurer securityMatcherConfigurer) {
        this.securityMetadataSourceStorage = securityMetadataSourceStorage;
        this.securityMatcherConfigurer = securityMatcherConfigurer;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

        final HttpServletRequest request = object.getRequest();

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (WebUtils.isStaticResources(url)) {
            log.trace("[FUCK] |- Is static resource : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        if (WebUtils.isPathMatch(securityMatcherConfigurer.getPermitAllList(), url)) {
            log.trace("[FUCK] |- Is white list resource : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        String feignInnerFlag = HeaderUtils.getHerodotusFromIn(request);
        if (StringUtils.isNotBlank(feignInnerFlag)) {
            log.trace("[FUCK] |- Is feign inner invoke : [{}], Passed!", url);
            return new AuthorizationDecision(true);
        }

        if (WebUtils.isPathMatch(securityMatcherConfigurer.getHasAuthenticatedList(), url)) {
            log.trace("[FUCK] |- Is has authenticated resource : [{}]", url);
            return new AuthorizationDecision(authentication.get().isAuthenticated());
        }

        List<HerodotusConfigAttribute> configAttributes = findConfigAttribute(url, method, request);
        if (CollectionUtils.isEmpty(configAttributes)) {
            log.warn("[FUCK] |- NO PRIVILEGES : [{}].", url);

            if (!securityMatcherConfigurer.getAuthorizationProperties().getStrict()) {
                if (authentication.get().isAuthenticated()) {
                    log.debug("[FUCK] |- Request is authenticated: [{}].", url);
                    return new AuthorizationDecision(true);
                }
            }

            return new AuthorizationDecision(false);
        }

        for (HerodotusConfigAttribute configAttribute : configAttributes) {
            WebExpressionAuthorizationManager webExpressionAuthorizationManager = new WebExpressionAuthorizationManager(configAttribute.getAttribute());
            AuthorizationDecision decision = webExpressionAuthorizationManager.check(authentication, object);
            if (decision.isGranted()) {
                log.debug("[FUCK] |- Request [{}] is authorized!", object.getRequest().getRequestURI());
                return decision;
            }
        }

        return new AuthorizationDecision(false);
    }

    private List<HerodotusConfigAttribute> findConfigAttribute(String url, String method, HttpServletRequest request) {

        log.debug("[FUCK] |- Current Request is : [{}] - [{}]", url, method);

        List<HerodotusConfigAttribute> configAttributes = this.securityMetadataSourceStorage.getConfigAttribute(url, method);
        if (CollectionUtils.isNotEmpty(configAttributes)) {
            log.debug("[FUCK] |- Get configAttributes from local storage for : [{}] - [{}]", url, method);
            return configAttributes;
        } else {
            LinkedHashMap<HerodotusRequest, List<HerodotusConfigAttribute>> compatible = this.securityMetadataSourceStorage.getCompatible();
            if (MapUtils.isNotEmpty(compatible)) {
                // 支持含有**通配符的路径搜索
                for (Map.Entry<HerodotusRequest, List<HerodotusConfigAttribute>> entry : compatible.entrySet()) {
                    HerodotusRequestMatcher requestMatcher = new HerodotusRequestMatcher(entry.getKey());
                    if (requestMatcher.matches(request)) {
                        log.debug("[FUCK] |- Request match the wildcard [{}] - [{}]", entry.getKey(), entry.getValue());
                        return entry.getValue();
                    }
                }
            }
        }

        return null;
    }
}
