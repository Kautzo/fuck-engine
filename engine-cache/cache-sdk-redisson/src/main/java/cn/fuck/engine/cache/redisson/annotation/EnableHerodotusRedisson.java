package cn.fuck.engine.cache.redisson.annotation;

import cn.fuck.engine.cache.redisson.configuration.CacheRedissonAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启Redisson注入 </p>
 * @date : 2022/1/14 23:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CacheRedissonAutoConfiguration.class)
public @interface EnableHerodotusRedisson {
}
