package cn.fuck.engine.rest.condition.properties;

import cn.fuck.engine.assistant.core.enums.Architecture;
import cn.fuck.engine.assistant.core.enums.Protocol;
import cn.fuck.engine.assistant.core.enums.Target;
import cn.fuck.engine.rest.condition.constants.RestConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 平台服务相关配置 </p>
 */
@Data
@ConfigurationProperties(prefix = RestConstants.PROPERTY_PREFIX_PLATFORM)
public class PlatformProperties {

    /**
     * 平台架构类型，默认：DISTRIBUTED（分布式架构）
     */
    private Architecture architecture = Architecture.DISTRIBUTED;
    /**
     * 数据访问策略，默认：远程
     */
    private Target dataAccessStrategy = Target.REMOTE;

    /**
     * 接口地址默认采用的Http协议类型
     */
    private Protocol protocol = Protocol.HTTP;

}
