package cn.fuck.engine.assistant.ip2region.properties;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: Ip2Region 配置参数 </p>
 */
@ConfigurationProperties(prefix = BaseConstants.PROPERTY_PREFIX_IP2REGION)
public class Ip2RegionProperties {

    /**
     * Ip V4 地址数据库位置，默认值：classpath*:/db/ip2region.db
     */
    private String ipV4Resource = "classpath:/db/ip2region.xdb";
    /**
     * Ip V6 地址数据库位置，默认值：classpath:db/ipv6wry.db
     */
    private String ipV6Resource = "classpath:/db/ipv6wry.db";

    public String getIpV4Resource() {
        return ipV4Resource;
    }

    public void setIpV4Resource(String ipV4Resource) {
        this.ipV4Resource = ipV4Resource;
    }

    public String getIpV6Resource() {
        return ipV6Resource;
    }

    public void setIpV6Resource(String ipV6Resource) {
        this.ipV6Resource = ipV6Resource;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ipV4Resource", ipV4Resource)
                .add("ipV6Resource", ipV6Resource)
                .toString();
    }
}
