package cn.fuck.engine.cache.redisson.annotation;

import cn.fuck.engine.cache.core.constants.CacheConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

/**
 * <p>Description: 是否开启 Redisson 条件注解 </p>
 * @date : 2021/10/22 14:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(value = CacheConstants.ITEM_REDISSON_ENABLED, havingValue = "true")
public @interface ConditionalOnRedissonEnabled {
}
