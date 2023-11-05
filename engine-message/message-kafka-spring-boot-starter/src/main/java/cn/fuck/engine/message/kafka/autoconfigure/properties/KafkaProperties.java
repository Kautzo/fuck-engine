package cn.fuck.engine.message.kafka.autoconfigure.properties;

import cn.fuck.engine.message.core.constants.MessageConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 消息队列配置 </p>
 */
@ConfigurationProperties(prefix = MessageConstants.PROPERTY_PREFIX_KAFKA)
public class KafkaProperties {

    /**
     * Kakfa监听是否自动启动
     */
    private Boolean enabled = false;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
