package cn.fuck.engine.cache.redis.annotation;

import cn.fuck.engine.cache.redis.configuration.CacheRedisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 Fuck Redis  </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheRedisConfiguration.class)
public @interface EnableFuckRedis {
}
