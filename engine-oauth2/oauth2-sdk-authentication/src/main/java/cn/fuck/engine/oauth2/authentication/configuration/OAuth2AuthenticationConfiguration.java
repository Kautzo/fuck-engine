package cn.fuck.engine.oauth2.authentication.configuration;

import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.oauth2.authentication.customizer.FuckJwtTokenCustomizer;
import cn.fuck.engine.oauth2.authentication.customizer.FuckOpaqueTokenCustomizer;
import cn.fuck.engine.oauth2.authentication.customizer.OAuth2ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.oauth2.authentication.customizer.OAuth2FormLoginConfigurerCustomizer;
import cn.fuck.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.fuck.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.fuck.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * <p>Description: OAuth2 认证基础模块配置 </p>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({OAuth2AuthenticationProperties.class})
public class OAuth2AuthenticationConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- SDK [OAuth2 Authentication] Auto Configure.");
    }

    @Bean
    public LockedUserDetailsStampManager lockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        LockedUserDetailsStampManager manager = new LockedUserDetailsStampManager(authenticationProperties);
        log.trace("[FUCK] |- Bean [Locked UserDetails Stamp Manager] Auto Configure.");
        return manager;
    }

    @Bean
    public SignInFailureLimitedStampManager signInFailureLimitedStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        SignInFailureLimitedStampManager manager = new SignInFailureLimitedStampManager(authenticationProperties);
        log.trace("[FUCK] |- Bean [SignIn Failure Limited Stamp Manager] Auto Configure.");
        return manager;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2FormLoginConfigurerCustomizer oauth2FormLoginConfigurerCustomer(OAuth2AuthenticationProperties authenticationProperties) {
        OAuth2FormLoginConfigurerCustomizer configurer = new OAuth2FormLoginConfigurerCustomizer(authenticationProperties);
        log.trace("[FUCK] |- Bean [OAuth2 FormLogin Configurer Customer] Auto Configure.");
        return configurer;
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
        FuckJwtTokenCustomizer customizer = new FuckJwtTokenCustomizer();
        log.trace("[FUCK] |- Bean [OAuth2 Jwt Token Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public OAuth2TokenCustomizer<OAuth2TokenClaimsContext> opaqueTokenCustomizer() {
        FuckOpaqueTokenCustomizer customizer = new FuckOpaqueTokenCustomizer();
        log.trace("[FUCK] |- Bean [OAuth2 Opaque Token Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer oauth2ErrorCodeMapperBuilderCustomizer() {
        OAuth2ErrorCodeMapperBuilderCustomizer customizer = new OAuth2ErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [OAuth2 ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
