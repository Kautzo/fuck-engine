package cn.fuck.engine.cache.jetcache.annotation;

import cn.fuck.engine.cache.jetcache.configuration.CacheJetCacheAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启JetCache注入 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheJetCacheAutoConfiguration.class)
public @interface EnableFuckJetCache {
}
