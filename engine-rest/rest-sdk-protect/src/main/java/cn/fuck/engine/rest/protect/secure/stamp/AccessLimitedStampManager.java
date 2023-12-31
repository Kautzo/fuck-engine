package cn.fuck.engine.rest.protect.secure.stamp;

import cn.fuck.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.fuck.engine.rest.condition.constants.RestConstants;
import cn.fuck.engine.rest.condition.properties.SecureProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Description: 防刷签章管理器 </p>
 * <p>
 * 这里使用Long类型作为值的存储类型，是为了解决该Cache 同时可以存储Duration相关的数据
 */
@Slf4j
public class AccessLimitedStampManager extends AbstractStampManager<String, Long> {

    private final SecureProperties secureProperties;

    public AccessLimitedStampManager(SecureProperties secureProperties) {
        super(RestConstants.CACHE_NAME_TOKEN_ACCESS_LIMITED);
        this.secureProperties = secureProperties;
    }

    public SecureProperties getSecureProperties() {
        return secureProperties;
    }

    @Override
    public Long nextStamp(String key) {
        return 1L;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(secureProperties.getAccessLimited().getExpire());
    }
}
