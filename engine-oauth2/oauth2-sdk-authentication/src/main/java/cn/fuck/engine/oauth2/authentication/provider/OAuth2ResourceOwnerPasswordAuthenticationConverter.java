package cn.fuck.engine.oauth2.authentication.provider;

import cn.fuck.engine.assistant.core.utils.http.SessionUtils;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2EndpointUtils;
import cn.fuck.engine.oauth2.core.definition.FuckGrantType;
import cn.fuck.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>Description: 自定义密码模式认证转换器 </p>
 * <p>
 * {@code AuthenticationConverter} 类似于以前的 {@code AbstractTokenGranter}
 * 主要用途是从请求中获取参数，并拼装Token类
 */
public final class OAuth2ResourceOwnerPasswordAuthenticationConverter extends AbstractAuthenticationConverter {

    public OAuth2ResourceOwnerPasswordAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
        super(httpCryptoProcessor);
    }

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!FuckGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        MultiValueMap<String, String> parameters = OAuth2EndpointUtils.getParameters(request);

        // scope (OPTIONAL)
        String scope = OAuth2EndpointUtils.checkOptionalParameter(parameters, OAuth2ParameterNames.SCOPE);

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(
                    Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        // username (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.USERNAME);

        // password (REQUIRED)
        OAuth2EndpointUtils.checkRequiredParameter(parameters, OAuth2ParameterNames.PASSWORD);

        String sessionId = SessionUtils.analyseSessionId(request);

        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
                    !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, (value.size() == 1) ? decrypt(sessionId, value.get(0)) : decrypt(sessionId, value));
            }
        });

        return new OAuth2ResourceOwnerPasswordAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);

    }
}
