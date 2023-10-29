package cn.fuck.engine.cache.jetcache.enhance;

import org.apache.ibatis.cache.Cache;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Description: 扩展的Mybatis二级缓存 </p>
 */
public class FuckMybatisCache implements Cache {

    private static final Logger log = LoggerFactory.getLogger(FuckMybatisCache.class);

    private final String id;
    private final com.alicp.jetcache.Cache<Object, Object> cache;
    private final AtomicInteger counter = new AtomicInteger(0);

    public FuckMybatisCache(String id) {
        this.id = id;
        JetCacheCreateCacheFactory jetCacheCreateCacheFactory = SpringUtil.getBean("jetCacheCreateCacheFactory");
        this.cache = jetCacheCreateCacheFactory.create(this.id);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
        counter.incrementAndGet();
        log.debug("[FUCK] |- CACHE - Put data into Mybatis Cache, with key: [{}]", key);
    }

    @Override
    public Object getObject(Object key) {
        Object obj = cache.get(key);
        log.debug("[FUCK] |- CACHE - Get data from Mybatis Cache, with key: [{}]", key);
        return obj;
    }

    @Override
    public Object removeObject(Object key) {
        Object obj = cache.remove(key);
        counter.decrementAndGet();
        log.debug("[FUCK] |- CACHE - Remove data from Mybatis Cache, with key: [{}]", key);
        return obj;
    }

    @Override
    public void clear() {
        cache.close();
        log.debug("[FUCK] |- CACHE - Clear Mybatis Cache.");
    }

    @Override
    public int getSize() {
        return counter.get();
    }
}
