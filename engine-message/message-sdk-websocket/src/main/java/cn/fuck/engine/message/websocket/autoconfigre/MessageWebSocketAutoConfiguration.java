package cn.fuck.engine.message.websocket.autoconfigre;

import cn.fuck.engine.message.websocket.annotation.ConditionalOnMultipleWebSocketInstance;
import cn.fuck.engine.message.websocket.annotation.ConditionalOnSingleWebSocketInstance;
import cn.fuck.engine.message.websocket.configuration.WebSocketMessageBrokerConfiguration;
import cn.fuck.engine.message.websocket.processor.MultipleInstanceMessageSender;
import cn.fuck.engine.message.websocket.processor.MultipleInstanceMessageSyncConsumer;
import cn.fuck.engine.message.websocket.processor.SingleInstanceMessageSender;
import cn.fuck.engine.assistant.core.definition.BearerTokenResolver;
import cn.fuck.engine.message.websocket.definition.WebSocketMessageSender;
import cn.fuck.engine.message.websocket.domain.WebSocketMessage;
import cn.fuck.engine.message.websocket.interceptor.WebSocketAuthenticationHandshakeInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;

import java.util.function.Consumer;

/**
 * <p>Description: WebSocket 处理器相关配置 </p>
 */
@AutoConfiguration
public class MessageWebSocketAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageWebSocketAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- Module [Message WebSocket] Auto Configure.");
    }

    @Bean
    public WebSocketAuthenticationHandshakeInterceptor webSocketPrincipalHandshakeHandler(BearerTokenResolver bearerTokenResolver) {
        WebSocketAuthenticationHandshakeInterceptor webSocketAuthenticationHandshakeInterceptor = new WebSocketAuthenticationHandshakeInterceptor(bearerTokenResolver);
        log.trace("[FUCK] |- Bean [WebSocket Authentication Handshake Interceptor] Auto Configure.");
        return webSocketAuthenticationHandshakeInterceptor;
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSingleWebSocketInstance
    static class SingleInstanceConfiguration {
        @Bean
        @ConditionalOnMissingBean
        public WebSocketMessageSender webSocketMessageSender(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
            SingleInstanceMessageSender singleInstanceMessageSender = new SingleInstanceMessageSender(simpMessagingTemplate, simpUserRegistry);
            log.debug("[FUCK] |- Strategy [Single Instance Web Socket Message Sender] Auto Configure.");
            return singleInstanceMessageSender;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnMultipleWebSocketInstance
    static class MultipleInstanceConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public WebSocketMessageSender webSocketMessageSender(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry, StreamBridge streamBridge) {
            MultipleInstanceMessageSender multipleInstanceMessageSender = new MultipleInstanceMessageSender(simpMessagingTemplate, simpUserRegistry, streamBridge);
            log.debug("[FUCK] |- Strategy [Single Instance Web Socket Message Sender] Auto Configure.");
            return multipleInstanceMessageSender;
        }

        @Bean
        public <T> Consumer<WebSocketMessage<T>> webSocketConsumer(WebSocketMessageSender webSocketMessageSender) {
            MultipleInstanceMessageSyncConsumer<T> multipleInstanceMessageSyncConsumer = new MultipleInstanceMessageSyncConsumer<>(webSocketMessageSender);
            log.trace("[FUCK] |- Bean [Multiple Instance Message Receiver] Auto Configure.");
            return multipleInstanceMessageSyncConsumer;
        }
    }

    @Configuration(proxyBeanMethods = false)
    @Import({
            WebSocketMessageBrokerConfiguration.class,
    })
    @ComponentScan(basePackages = {
            "cn.fuck.engine.message.websocket.controller",
            "cn.fuck.engine.message.websocket.listener",
    })
    static class WebSocketConfiguration {

    }
}
