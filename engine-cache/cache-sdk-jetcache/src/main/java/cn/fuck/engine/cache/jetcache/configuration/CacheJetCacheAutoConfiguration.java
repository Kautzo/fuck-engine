package cn.fuck.engine.cache.jetcache.configuration;

import cn.fuck.engine.cache.jetcache.utils.JetCacheUtils;
import cn.fuck.engine.cache.caffeine.configuration.CacheCaffeineConfiguration;
import cn.fuck.engine.cache.core.properties.CacheProperties;
import cn.fuck.engine.cache.jetcache.enhance.FuckCacheManager;
import cn.fuck.engine.cache.jetcache.enhance.JetCacheCreateCacheFactory;
import cn.fuck.engine.cache.redis.configuration.CacheRedisConfiguration;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.autoconfigure.JetCacheAutoConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * <p>Description: JetCacheConfiguration </p>
 * <p>
 * 新增JetCache配置，解决JetCache依赖循环问题
 * <p>
 * 注解 @AutoConfiguration 它是 @Configuration、@AutoConfigureBefore、@AutoConfigureAfter三个注解结合体，以一顶三。
 * 标注 @AutoConfiguration 注解的类也必须放进
 * META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 文件里才算自动配置类，否则也只是普通配置类而已
 */
@AutoConfiguration(after = JetCacheAutoConfiguration.class)
@ConditionalOnClass({CacheManager.class})
@EnableConfigurationProperties(CacheProperties.class)
@Import({CacheCaffeineConfiguration.class, CacheRedisConfiguration.class})
public class CacheJetCacheAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheJetCacheAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Cache JetCache] Auto Configure.");
    }

    @Bean
    public JetCacheCreateCacheFactory jetCacheCreateCacheFactory(@Qualifier("jcCacheManager") CacheManager cacheManager,
                                                                 CacheProperties cacheProperties) {
        JetCacheCreateCacheFactory factory = new JetCacheCreateCacheFactory(cacheManager, cacheProperties);
        JetCacheUtils.setJetCacheCreateCacheFactory(factory);
        log.trace("[FUCK] |- Bean [Jet Cache Create Cache Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public FuckCacheManager fuckCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory,
                                             CacheProperties cacheProperties) {
        FuckCacheManager fuckCacheManager = new FuckCacheManager(jetCacheCreateCacheFactory, cacheProperties);
        fuckCacheManager.setAllowNullValues(cacheProperties.getAllowNullValues());
        log.trace("[FUCK] |- Bean [Jet Cache FUCK Cache Manager] Auto Configure.");
        return fuckCacheManager;
    }
}
