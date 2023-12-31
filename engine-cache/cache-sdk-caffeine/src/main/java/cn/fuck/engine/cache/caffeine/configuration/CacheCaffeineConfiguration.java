package cn.fuck.engine.cache.caffeine.configuration;

import cn.fuck.engine.cache.caffeine.enhance.FuckCaffeineCacheManager;
import cn.fuck.engine.cache.core.properties.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: Caffeine 配置 </p>
 */
@Configuration(proxyBeanMethods = false)
public class CacheCaffeineConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheCaffeineConfiguration.class);

    private final CacheProperties cacheProperties;

    public CacheCaffeineConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Cache Caffeine] Auto Configure.");
    }

    @Bean
    public Caffeine<Object, Object> caffeine() {
        Caffeine<Object, Object> caffeine = Caffeine
                .newBuilder()
                .expireAfterWrite(ObjectUtils.isNotEmpty(cacheProperties.getLocalExpire()) ? cacheProperties.getLocalExpire() : cacheProperties.getExpire());

        log.trace("[FUCK] |- Bean [Caffeine] Auto Configure.");

        return caffeine;
    }

    @Bean
    @ConditionalOnMissingBean(CaffeineCacheManager.class)
    public CaffeineCacheManager caffeineCacheManager(Caffeine<Object, Object> caffeine) {
        FuckCaffeineCacheManager fuckCaffeineCacheManager = new FuckCaffeineCacheManager(cacheProperties);
        fuckCaffeineCacheManager.setCaffeine(caffeine);
        log.trace("[FUCK] |- Bean [Caffeine Cache Manager] Auto Configure.");
        return fuckCaffeineCacheManager;
    }
}
