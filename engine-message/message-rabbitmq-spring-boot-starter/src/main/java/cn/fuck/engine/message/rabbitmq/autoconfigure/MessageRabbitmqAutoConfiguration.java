package cn.fuck.engine.message.rabbitmq.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * <p>Description: RabbitMQ Message 模块自动注入配置 </p>
 */
@AutoConfiguration
public class MessageRabbitmqAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageRabbitmqAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Message RabbiMQ Starter] Auto Configure.");
    }
}
