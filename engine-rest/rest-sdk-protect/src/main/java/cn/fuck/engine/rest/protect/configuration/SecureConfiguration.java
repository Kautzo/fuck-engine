package cn.fuck.engine.rest.protect.configuration;

import cn.fuck.engine.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import cn.fuck.engine.rest.condition.properties.SecureProperties;
import cn.fuck.engine.rest.protect.secure.interceptor.AccessLimitedInterceptor;
import cn.fuck.engine.rest.protect.secure.interceptor.IdempotentInterceptor;
import cn.fuck.engine.rest.protect.secure.interceptor.XssHttpServletFilter;
import cn.fuck.engine.rest.protect.secure.stamp.AccessLimitedStampManager;
import cn.fuck.engine.rest.protect.secure.stamp.IdempotentStampManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 接口安全配置 </p>
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({SecureProperties.class})
public class SecureConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SecureConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Protect Secure] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public IdempotentStampManager idempotentStampManager(SecureProperties secureProperties,
                                                         JetCacheCreateCacheFactory jetCacheCreateCacheFactory) {
        IdempotentStampManager idempotentStampManager = new IdempotentStampManager(secureProperties);
        log.trace("[FUCK] |- Bean [Idempotent Stamp Manager] Auto Configure.");
        return idempotentStampManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessLimitedStampManager accessLimitedStampManager(SecureProperties secureProperties) {
        AccessLimitedStampManager accessLimitedStampManager = new AccessLimitedStampManager(secureProperties);
        log.trace("[FUCK] |- Bean [Access Limited Stamp Manager] Auto Configure.");
        return accessLimitedStampManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(IdempotentStampManager.class)
    public IdempotentInterceptor idempotentInterceptor(IdempotentStampManager idempotentStampManager) {
        IdempotentInterceptor idempotentInterceptor = new IdempotentInterceptor();
        idempotentInterceptor.setIdempotentStampManager(idempotentStampManager);
        log.trace("[FUCK] |- Bean [Idempotent Interceptor] Auto Configure.");
        return idempotentInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(AccessLimitedStampManager.class)
    public AccessLimitedInterceptor accessLimitedInterceptor(AccessLimitedStampManager accessLimitedStampManager) {
        AccessLimitedInterceptor accessLimitedInterceptor = new AccessLimitedInterceptor();
        accessLimitedInterceptor.setAccessLimitedStampManager(accessLimitedStampManager);
        log.trace("[FUCK] |- Bean [Access Limited Interceptor] Auto Configure.");
        return accessLimitedInterceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public XssHttpServletFilter xssHttpServletFilter() {
        XssHttpServletFilter xssHttpServletFilter = new XssHttpServletFilter();
        log.trace("[FUCK] |- Bean [Xss Http Servlet Filter] Auto Configure.");
        return xssHttpServletFilter;
    }
}
