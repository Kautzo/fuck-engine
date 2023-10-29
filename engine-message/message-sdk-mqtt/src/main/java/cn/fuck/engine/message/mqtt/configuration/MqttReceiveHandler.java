package cn.fuck.engine.message.mqtt.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>Description: TODO </p>
 * @date : 2023/9/11 16:08
 */
@Configuration(proxyBeanMethods = false)
public class MqttReceiveHandler {

    private static final Logger log = LoggerFactory.getLogger(MqttReceiveHandler.class);

    @Bean
    public MessageHandler handlerMqtt5Message() {
        return message -> {
            MessageHeaders headers = message.getHeaders();
            //获取消息Topic
            String receivedTopic = (String) headers.get(MqttHeaders.RECEIVED_TOPIC);
            log.info("获取到v5的消息的topic :{} ", receivedTopic);
            String responseTopic = (String) headers.get(MqttHeaders.RESPONSE_TOPIC);
            log.info("获取到v5的消息的响应主题 :{} ", responseTopic);

            String correlationData = null;
            if (Objects.nonNull(headers.get(MqttHeaders.CORRELATION_DATA))) {
                correlationData = new String((byte[]) Objects.requireNonNull(headers.get(MqttHeaders.CORRELATION_DATA)), StandardCharsets.UTF_8);
                log.info("获取到v5的消息关联数据 :{} ", correlationData);
            }
            //获取消息体
            String payload = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
            log.info("获取到v5的消息的payload :{} ", payload);
        };
    }
}
