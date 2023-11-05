package cn.fuck.engine.oauth2.authorization.configuration;

import cn.fuck.engine.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import cn.fuck.engine.oauth2.authorization.auditing.FuckMetaObjectHandler;
import cn.fuck.engine.oauth2.authorization.customizer.OAuth2AuthorizeHttpRequestsConfigurerCustomer;
import cn.fuck.engine.oauth2.authorization.customizer.OAuth2ResourceServerConfigurerCustomer;
import cn.fuck.engine.oauth2.authorization.properties.OAuth2AuthorizationProperties;
import cn.fuck.engine.assistant.core.definition.BearerTokenResolver;
import cn.fuck.engine.oauth2.authorization.listener.RemoteSecurityMetadataSyncListener;
import cn.fuck.engine.oauth2.authorization.processor.SecurityAuthorizationManager;
import cn.fuck.engine.oauth2.authorization.processor.SecurityMatcherConfigurer;
import cn.fuck.engine.oauth2.authorization.processor.SecurityMetadataSourceAnalyzer;
import cn.fuck.engine.oauth2.authorization.processor.SecurityMetadataSourceStorage;
import cn.fuck.engine.oauth2.core.exception.SecurityGlobalExceptionHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;

/**
 * <p>Description: SecurityAttribute 配置 </p>
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties({OAuth2AuthorizationProperties.class})
@EnableMethodSecurity(proxyTargetClass = true)
@Import({
        SecurityGlobalExceptionHandler.class,
        OAuth2SessionConfiguration.class,
})
public class OAuth2AuthorizationConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- SDK [OAuth2 Authorization] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataSourceStorage securityMetadataSourceStorage(JetCacheCreateCacheFactory jetCacheCreateCacheFactory) {
        SecurityMetadataSourceStorage securityMetadataSourceStorage = new SecurityMetadataSourceStorage();
        log.trace("[FUCK] |- Bean [Security Metadata Source Storage] Auto Configure.");
        return securityMetadataSourceStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMatcherConfigurer securityMatcherConfigurer(OAuth2AuthorizationProperties authorizationProperties) {
        SecurityMatcherConfigurer securityMatcherConfigurer = new SecurityMatcherConfigurer(authorizationProperties);
        log.trace("[FUCK] |- Bean [Security Metadata Configurer] Auto Configure.");
        return securityMatcherConfigurer;
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityAuthorizationManager securityAuthorizationManager(SecurityMetadataSourceStorage securityMetadataSourceStorage,
                                                                     SecurityMatcherConfigurer securityMatcherConfigurer) {
        SecurityAuthorizationManager securityAuthorizationManager = new SecurityAuthorizationManager(securityMetadataSourceStorage, securityMatcherConfigurer);
        log.trace("[FUCK] |- Bean [Authorization Manager] Auto Configure.");
        return securityAuthorizationManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2AuthorizeHttpRequestsConfigurerCustomer authorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer,
                                                                                                 SecurityAuthorizationManager securityAuthorizationManager) {
        OAuth2AuthorizeHttpRequestsConfigurerCustomer OAuth2AuthorizeHttpRequestsConfigurerCustomer = new OAuth2AuthorizeHttpRequestsConfigurerCustomer(securityMatcherConfigurer, securityAuthorizationManager);
        log.trace("[FUCK] |- Bean [Authorize Http Requests Configurer Customer] Auto Configure.");
        return OAuth2AuthorizeHttpRequestsConfigurerCustomer;
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer(SecurityMetadataSourceStorage securityMetadataSourceStorage,
                                                                         SecurityMatcherConfigurer securityMatcherConfigurer) {
        SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer = new SecurityMetadataSourceAnalyzer(securityMetadataSourceStorage, securityMatcherConfigurer);
        log.trace("[FUCK] |- Bean [Security Metadata Source Analyzer] Auto Configure.");
        return securityMetadataSourceAnalyzer;
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener(SecurityMetadataSourceAnalyzer securityMetadataSourceAnalyzer,
                                                                                 ServiceMatcher serviceMatcher) {
        RemoteSecurityMetadataSyncListener remoteSecurityMetadataSyncListener = new RemoteSecurityMetadataSyncListener(securityMetadataSourceAnalyzer, serviceMatcher);
        log.trace("[FUCK] |- Bean [Security Metadata Refresh Listener] Auto Configure.");
        return remoteSecurityMetadataSyncListener;
    }

    @Bean
    @ConditionalOnMissingBean
    public OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer(OAuth2AuthorizationProperties authorizationProperties,
                                                                                         JwtDecoder jwtDecoder,
                                                                                         OAuth2ResourceServerProperties resourceServerProperties) {
        OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer = new OAuth2ResourceServerConfigurerCustomer(authorizationProperties, jwtDecoder, resourceServerProperties);
        log.trace("[FUCK] |- Bean [OAuth2 Resource Server Configurer Customer] Auto Configure.");
        return oauth2ResourceServerConfigurerCustomer;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(OAuth2ResourceServerConfigurerCustomer.class)
    public BearerTokenResolver bearerTokenResolver(OAuth2ResourceServerConfigurerCustomer oauth2ResourceServerConfigurerCustomer) {
        BearerTokenResolver bearerTokenResolver = oauth2ResourceServerConfigurerCustomer.createBearerTokenResolver();
        log.trace("[FUCK] |- Bean [Bearer Token Resolver] Auto Configure.");
        return bearerTokenResolver;
    }

    @Bean
    public MetaObjectHandler mybatisPlusMetaObjectHandler() {
        FuckMetaObjectHandler fuckMetaObjectHandler = new FuckMetaObjectHandler();
        log.debug("[FUCK] |- Bean [Fuck MetaObject Handler] Auto Configure.");
        return fuckMetaObjectHandler;
    }
}
