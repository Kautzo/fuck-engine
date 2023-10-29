package cn.fuck.engine.cache.core.properties;

import cn.fuck.engine.cache.core.enums.CacheMethod;
import lombok.Data;

import java.time.Duration;

/**
 * <p>Description: 自定义二级缓存过期时间通用属性 </p>
 */
@Data
public class CacheSetting {

    /**
     * 指定缓存区域，与 JetCache Area 配置对应。
     */
    private String area;
    /**
     * 缓存方式。默认：多级缓存
     */
    private CacheMethod method = CacheMethod.BOTH;
    /**
     * 缓存过期时间，默认两小时
     * <p>
     * 使用Duration类型，配置参数形式如下：
     * "?ns" //纳秒
     * "?us" //微秒
     * "?ms" //毫秒
     * "?s" //秒
     * "?m" //分
     * "?h" //小时
     * "?d" //天
     */
    private Duration expire = Duration.ofHours(2);
    /**
     * 是否开启多实例本地缓存同步，默认：不开启
     * 仅在多级缓存模式下生效。
     */
    private Boolean sync = false;

    /**
     * 本地缓存过期时间
     */
    private Duration localExpire;
    /**
     * 本地缓存数量限制。
     */
    private Integer localLimit;
    /**
     * 是否开启缓存穿透保护, 默认：true
     */
    private Boolean penetrationProtect;
    /**
     * 缓存穿透保护有效期，默认：2 小时。
     */
    private Duration penetrationProtectTimeout;

}
