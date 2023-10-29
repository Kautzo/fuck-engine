package cn.fuck.engine.oauth2.authorization.configuration;

import cn.fuck.engine.oauth2.authorization.customizer.OAuth2SessionManagementConfigurerCustomer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * <p>Description: OAuth2 Session 共享配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableRedisIndexedHttpSession
public class OAuth2SessionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OAuth2SessionConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- SDK [OAuth2 Session Sharing] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public <S extends Session> SessionRegistry sessionRegistry(FindByIndexNameSessionRepository<S> sessionRepository) {
        SpringSessionBackedSessionRegistry<S> springSessionBackedSessionRegistry = new SpringSessionBackedSessionRegistry<>(sessionRepository);
        log.trace("[FUCK] |- Bean [Spring Session Backed Session Registry] Auto Configure.");
        return springSessionBackedSessionRegistry;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2SessionManagementConfigurerCustomer sessionManagementConfigurerCustomer(SessionRegistry sessionRegistry) {
        OAuth2SessionManagementConfigurerCustomer OAuth2SessionManagementConfigurerCustomer = new OAuth2SessionManagementConfigurerCustomer(sessionRegistry);
        log.trace("[FUCK] |- Bean [Session Management Configurer Customer] Auto Configure.");
        return OAuth2SessionManagementConfigurerCustomer;
    }

    /**
     * If a SessionRegistry @Bean is registered and is an instance of SessionRegistryImpl,
     * a HttpSessionEventPublisher @Bean SHOULD also be registered as it’s responsible for notifying SessionRegistryImpl of session lifecycle events, f
     * or example, SessionDestroyedEvent, to provide the ability to remove the SessionInformation instance.
     *
     * @return {@link HttpSessionEventPublisher}
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
