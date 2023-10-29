package cn.fuck.engine.cache.core.constants;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;

/**
 * <p>Description: Cache Property值常量 </p>
 */
public interface CacheConstants extends BaseConstants {

    String PROPERTY_REDIS_REDISSON = PROPERTY_SPRING_DATA + ".redisson";
    String ITEM_REDISSON_ENABLED = PROPERTY_REDIS_REDISSON + PROPERTY_ENABLED;
}
