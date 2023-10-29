package cn.fuck.engine.message.kafka.autoconfigure;

import cn.fuck.engine.message.kafka.autoconfigure.configuration.KafkaConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Kafka Message 模块自动注入配置 </p>
 * @date : 2022/1/20 19:07
 */
@AutoConfiguration
@Import({KafkaConfiguration.class})
public class MessageKafkaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageKafkaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Message Kafka Starter] Auto Configure.");
    }
}
