package cn.fuck.engine.cache.jetcache.enhance;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.cache.core.properties.CacheProperties;
import cn.fuck.engine.cache.core.properties.CacheSetting;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.Map;

/**
 * <p>Description: 自定义 缓存管理器 </p>
 */
public class FuckCacheManager extends JetCacheSpringCacheManager {

    private static final Logger log = LoggerFactory.getLogger(FuckCacheManager.class);

    private final CacheProperties cacheProperties;

    public FuckCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties) {
        super(jetCacheCreateCacheFactory);
        this.cacheProperties = cacheProperties;
        this.setAllowNullValues(cacheProperties.getAllowNullValues());
    }

    public FuckCacheManager(JetCacheCreateCacheFactory jetCacheCreateCacheFactory, CacheProperties cacheProperties, String... cacheNames) {
        super(jetCacheCreateCacheFactory, cacheNames);
        this.cacheProperties = cacheProperties;
    }

    @Override
    protected Cache createJetCache(String name) {
        Map<String, CacheSetting> instances = cacheProperties.getInstances();
        if (MapUtils.isNotEmpty(instances)) {
            String key = StringUtils.replace(name, SymbolConstants.COLON, cacheProperties.getSeparator());
            if (instances.containsKey(key)) {
                CacheSetting cacheSetting = instances.get(key);
                log.debug("[FUCK] |- CACHE - Cache [{}] is set to use INSTANCE cache.", name);
                return super.createJetCache(name, cacheSetting);
            }
        }
        return super.createJetCache(name);
    }
}
