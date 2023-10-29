package cn.fuck.engine.oauth2.authorization.customizer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;

/**
 * <p>Description: 自定义扩展 SessionAuthenticationStrategy </p>
 *
 * 扩展 SessionAuthenticationStrategy 以支持 {@link FindByIndexNameSessionRepository#PRINCIPAL_NAME_INDEX_NAME} 的设置。
 * @date : 2023/9/5 14:01
 */
public class HerodotusSessionAuthenticationStrategy extends RegisterSessionAuthenticationStrategy {

    public HerodotusSessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        if (ObjectUtils.isNotEmpty(authentication) && authentication.isAuthenticated()) {
            if (authentication instanceof BearerTokenAuthentication) {
                request.getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, authentication.getName());
            }
        }
        super.onAuthentication(authentication, request, response);
    }
}
