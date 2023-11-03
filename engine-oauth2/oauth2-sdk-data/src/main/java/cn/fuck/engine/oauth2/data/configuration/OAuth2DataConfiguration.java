package cn.fuck.engine.oauth2.data.configuration;

import cn.fuck.engine.oauth2.data.service.impl.FuckAuthorizationConsentServiceImpl;
import cn.fuck.engine.oauth2.data.service.impl.FuckAuthorizationServiceImpl;
import cn.fuck.engine.oauth2.data.service.impl.FuckRegisteredClientServiceImpl;
import cn.fuck.engine.oauth2.data.storage.FuckOAuth2AuthorizationConsentService;
import cn.fuck.engine.oauth2.data.storage.FuckOAuth2AuthorizationService;
import cn.fuck.engine.oauth2.data.storage.FuckRegisteredClientRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: OAuth2 Manager 模块配置 </p>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {
        "cn.fuck.engine.oauth2"
})
public class OAuth2DataConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [OAuth2 Data JPA] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public RegisteredClientRepository registeredClientRepository(FuckRegisteredClientServiceImpl fuckRegisteredClientServiceImpl, PasswordEncoder passwordEncoder) {
        FuckRegisteredClientRepository fuckRegisteredClientRepository = new FuckRegisteredClientRepository(fuckRegisteredClientServiceImpl, passwordEncoder);
        log.debug("[FUCK] |- Bean [Jpa Registered Client Repository] Auto Configure.");
        return fuckRegisteredClientRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationService authorizationService(FuckAuthorizationServiceImpl fuckAuthorizationServiceImpl, RegisteredClientRepository registeredClientRepository) {
        FuckOAuth2AuthorizationService fuckOAuth2AuthorizationService = new FuckOAuth2AuthorizationService(fuckAuthorizationServiceImpl, registeredClientRepository);
        log.debug("[FUCK] |- Bean [Jpa OAuth2 Authorization Service] Auto Configure.");
        return fuckOAuth2AuthorizationService;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizationConsentService authorizationConsentService(FuckAuthorizationConsentServiceImpl fuckAuthorizationConsentServiceImpl, RegisteredClientRepository registeredClientRepository) {
        FuckOAuth2AuthorizationConsentService fuckOAuth2AuthorizationConsentService = new FuckOAuth2AuthorizationConsentService(fuckAuthorizationConsentServiceImpl, registeredClientRepository);
        log.debug("[FUCK] |- Bean [Jpa OAuth2 Authorization Consent Service] Auto Configure.");
        return fuckOAuth2AuthorizationConsentService;
    }
}
