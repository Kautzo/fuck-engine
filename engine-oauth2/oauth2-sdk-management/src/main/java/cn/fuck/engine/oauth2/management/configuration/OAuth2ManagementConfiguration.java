package cn.fuck.engine.oauth2.management.configuration;

import cn.fuck.engine.oauth2.authentication.configuration.OAuth2AuthenticationConfiguration;
import cn.fuck.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import cn.fuck.engine.oauth2.data.configuration.OAuth2DataConfiguration;
import cn.fuck.engine.oauth2.management.compliance.listener.AuthenticationSuccessListener;
import cn.fuck.engine.oauth2.management.response.OAuth2DeviceVerificationResponseHandler;
import cn.fuck.engine.oauth2.management.response.OidcClientRegistrationResponseHandler;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 * <p>
 * {@link org.springframework.security.oauth2.jwt.JwtTimestampValidator}
 */
@Slf4j
@AutoConfiguration
@Import({OAuth2DataConfiguration.class, OAuth2AuthenticationConfiguration.class, OAuth2ComplianceConfiguration.class})
@ComponentScan(basePackages = {
        "cn.fuck.engine.oauth2.management.manager",
        "cn.fuck.engine.oauth2.management.service",
        "cn.fuck.engine.oauth2.management.controller",
})
public class OAuth2ManagementConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- Module [OAuth2 Management] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessListener authenticationSuccessListener(SignInFailureLimitedStampManager stampManager) {
        AuthenticationSuccessListener listener = new AuthenticationSuccessListener(stampManager);
        log.trace("[FUCK] |- Bean [OAuth2 Authentication Success Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2DeviceVerificationResponseHandler oauth2DeviceVerificationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OAuth2DeviceVerificationResponseHandler handler = new OAuth2DeviceVerificationResponseHandler(oauth2DeviceService);
        log.trace("[FUCK] |- Bean [OAuth2 Device Verification Response Handler] Auto Configure.");
        return handler;
    }

    @Bean
    @ConditionalOnMissingBean
    public OidcClientRegistrationResponseHandler oidcClientRegistrationResponseHandler(OAuth2DeviceService oauth2DeviceService) {
        OidcClientRegistrationResponseHandler handler = new OidcClientRegistrationResponseHandler(oauth2DeviceService);
        log.trace("[FUCK] |- Bean [Oidc Client Registration Response Handler] Auto Configure.");
        return handler;
    }

}
