package cn.fuck.engine.oauth2.management.compliance.listener;

import cn.fuck.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import cn.fuck.engine.cache.core.exception.MaximumLimitExceededException;
import cn.fuck.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.time.Duration;
import java.util.Map;

/**
 * <p>Description: 登出成功监听 </p>
 */
public class AuthenticationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFailureListener.class);

    private final SignInFailureLimitedStampManager stampManager;
    private final OAuth2AccountStatusManager accountStatusManager;

    public AuthenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusManager accountStatusManager) {
        this.stampManager = stampManager;
        this.accountStatusManager = accountStatusManager;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {

        log.debug("[FUCK] |- User sign in catch failure event : [{}].", event.getClass().getName());

        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            Authentication authentication = event.getAuthentication();

            String username = null;

            if (authentication instanceof OAuth2AuthorizationGrantAuthenticationToken) {

                log.debug("[FUCK] |- Toke object in failure event  is OAuth2AuthorizationGrantAuthenticationToken");

                OAuth2AuthorizationGrantAuthenticationToken token = (OAuth2AuthorizationGrantAuthenticationToken) authentication;
                Map<String, Object> params = token.getAdditionalParameters();
                username = getPrincipal(params);
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {

                log.debug("[FUCK] |- Toke object in failure event  is UsernamePasswordAuthenticationToken");

                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
                Object principal = token.getPrincipal();
                if (principal instanceof String) {
                    username = (String) principal;
                }
            }

            if (StringUtils.isNotBlank(username)) {

                log.debug("[FUCK] |- Parse the username in failure event is [{}].", username);

                int maxTimes = stampManager.getAuthenticationProperties().getSignInFailureLimited().getMaxTimes();
                Duration expire = stampManager.getAuthenticationProperties().getSignInFailureLimited().getExpire();
                try {
                    int times = stampManager.counting(username, maxTimes, expire, true, "AuthenticationFailureListener");
                    log.debug("[FUCK] |- Sign in user input password error [{}] items", times);
                } catch (MaximumLimitExceededException e) {
                    log.warn("[FUCK] |- User [{}] password error [{}] items, LOCK ACCOUNT!", username, maxTimes);
                    accountStatusManager.lock(username);
                }
            }
        }
    }

    private String getPrincipal(Map<String, Object> params) {
        if (MapUtils.isNotEmpty(params)) {
            if (params.containsKey(OAuth2ParameterNames.USERNAME)) {
                Object value = params.get(OAuth2ParameterNames.USERNAME);
                if (ObjectUtils.isNotEmpty(value)) {
                    return (String) value;
                }
            }
        }

        return null;
    }
}
