package cn.fuck.engine.oauth2.authorization.auditing;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Optional;

/**
 * <p>Description: 基于 Security 的数据库审计用户信息获取 </p>
 */
@Slf4j
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        SecurityContext context = SecurityContextHolder.getContext();

        if (ObjectUtils.isNotEmpty(context)) {
            Authentication authentication = context.getAuthentication();
            if (ObjectUtils.isNotEmpty(authentication)) {
                if (authentication.isAuthenticated()) {
                    if (authentication instanceof BearerTokenAuthentication bearerTokenAuthentication) {
                        Object object = bearerTokenAuthentication.getPrincipal();
                        if (object instanceof OAuth2IntrospectionAuthenticatedPrincipal principal) {
                            String username = principal.getName();
                            log.trace("[FUCK] |- Current auditor is : [{}]", username);
                            return Optional.of(username);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }
}
