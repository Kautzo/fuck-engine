package cn.fuck.engine.cache.caffeine.enhance;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.cache.core.properties.CacheProperties;
import cn.fuck.engine.cache.core.properties.CacheSetting;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;

/**
 * <p>Description: 扩展的 CaffeineCacheManager </p>
 * <p>
 * 用于支持 Caffeine 缓存可以针对实体进行单独的过期时间设定
 */
public class FuckCaffeineCacheManager extends CaffeineCacheManager {

    private static final Logger log = LoggerFactory.getLogger(FuckCaffeineCacheManager.class);

    private final CacheProperties cacheProperties;

    public FuckCaffeineCacheManager(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public FuckCaffeineCacheManager(CacheProperties cacheProperties, String... cacheNames) {
        super(cacheNames);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    @Override
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Map<String, CacheSetting> instances = cacheProperties.getInstances();
        if (MapUtils.isNotEmpty(instances)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (instances.containsKey(key)) {
                CacheSetting cacheSetting = instances.get(key);
                log.debug("[FUCK] |- CACHE - Caffeine cache [{}] is set to use INSTANCE config.", name);
                return Caffeine.newBuilder().expireAfterWrite(cacheSetting.getExpire()).build();
            }
        }

        return super.createNativeCaffeineCache(name);
    }
}
