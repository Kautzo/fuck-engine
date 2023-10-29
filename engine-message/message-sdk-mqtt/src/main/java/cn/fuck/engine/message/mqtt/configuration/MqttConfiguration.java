package cn.fuck.engine.message.mqtt.configuration;

import cn.fuck.engine.assistant.core.utils.type.ListUtils;
import cn.fuck.engine.message.mqtt.properties.MqttProperties;
import jakarta.annotation.PostConstruct;
import org.dromara.hutool.core.util.ByteUtil;
import org.eclipse.paho.mqttv5.client.IMqttAsyncClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.ClientManager;
import org.springframework.integration.mqtt.core.Mqttv5ClientManager;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.Mqttv5PahoMessageHandler;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * <p>Description: Mqtt 模块配置 </p>
 * @date : 2023/9/10 17:24
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MqttProperties.class)
public class MqttConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MqttConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Message Mqtt] Auto Configure.");
    }

    @Bean
    public ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager(MqttProperties mqttProperties) {
        MqttConnectionOptions mqttConnectionOptions = new MqttConnectionOptions();
        mqttConnectionOptions.setUserName(mqttProperties.getUsername());
        mqttConnectionOptions.setPassword(ByteUtil.toBytes(mqttProperties.getPassword(), StandardCharsets.UTF_8));
        mqttConnectionOptions.setCleanStart(mqttProperties.getCleanStart());
        mqttConnectionOptions.setKeepAliveInterval(toInt(mqttProperties.getKeepAliveInterval()));
        mqttConnectionOptions.setServerURIs(ListUtils.toStringArray(mqttProperties.getServerUrls()));
        mqttConnectionOptions.setAutomaticReconnect(mqttProperties.getAutomaticReconnect());
        mqttConnectionOptions.setAutomaticReconnectDelay(toInt(mqttProperties.getAutomaticReconnectMinDelay()), toInt(mqttProperties.getAutomaticReconnectMaxDelay()));
        log.info("[FUCK] |- Bean [Mqtt Connection Options] Auto Configure.");
        Mqttv5ClientManager clientManager = new Mqttv5ClientManager(mqttConnectionOptions, mqttProperties.getClientId());
        clientManager.setPersistence(new MqttDefaultFilePersistence());
        return clientManager;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqtt5OutboundChannel")
    public IntegrationFlow mqtt5InFlowTopic2(ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager) {
        Mqttv5PahoMessageDrivenChannelAdapter messageProducer = new Mqttv5PahoMessageDrivenChannelAdapter(clientManager, "topic2");
        return IntegrationFlow.from(messageProducer)
                .channel(c -> c.queue("fromMqttChannel"))
                .get();
    }

    @Bean
    public IntegrationFlow mqttOutFlow(ClientManager<IMqttAsyncClient, MqttConnectionOptions> clientManager) {

        return f -> f.handle(new Mqttv5PahoMessageHandler(clientManager));
    }

    private int toInt(Duration duration) {
        long value = duration.getSeconds();
        return Long.valueOf(value).intValue();
    }
}


