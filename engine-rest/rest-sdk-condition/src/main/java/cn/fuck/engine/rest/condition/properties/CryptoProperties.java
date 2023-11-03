package cn.fuck.engine.rest.condition.properties;

import cn.fuck.engine.rest.condition.constants.RestConstants;
import cn.fuck.engine.rest.core.enums.CryptoStrategy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 加密算法配置 </p>
 */
@Data
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_CRYPTO)
public class CryptoProperties {

    /**
     * 加密算法策略，默认：国密算法
     */
    private CryptoStrategy cryptoStrategy = CryptoStrategy.SM;

}
