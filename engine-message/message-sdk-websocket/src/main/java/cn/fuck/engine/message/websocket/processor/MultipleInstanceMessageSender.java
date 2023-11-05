package cn.fuck.engine.message.websocket.processor;

import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.message.core.exception.IllegalChannelException;
import cn.fuck.engine.message.core.exception.PrincipalNotFoundException;
import cn.fuck.engine.message.websocket.domain.WebSocketMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;

/**
 * <p>Description: Web Socket 多实例服务端消息发送 </p>
 */
public class MultipleInstanceMessageSender extends SingleInstanceMessageSender {

    private static final Logger log = LoggerFactory.getLogger(MultipleInstanceMessageSender.class);

    private final StreamBridge streamBridge;

    public MultipleInstanceMessageSender(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry, StreamBridge streamBridge) {
        super(simpMessagingTemplate, simpUserRegistry);
        this.streamBridge = streamBridge;
    }

    /**
     * 发送给指定用户信息。
     *
     * @param webSocketMessage 发送内容参数实体 {@link WebSocketMessage}
     * @param <T>              指定 payload 类型
     * @throws IllegalChannelException    Web Socket 通道设置错误
     * @throws PrincipalNotFoundException 该服务中无法找到与 identity 对应的用户 Principal
     */
    @Override
    public <T> void toUser(WebSocketMessage<T> webSocketMessage) throws IllegalChannelException, PrincipalNotFoundException {
        // 用户在当前实例中
        if (isUserExist(webSocketMessage.getTo())) {
            super.toUser(webSocketMessage);
        } else {
            syncMessageToOtherInstances(webSocketMessage);
        }
    }

    @Override
    public <T> void sendNoticeToAll(T payload) {
        syncBroadcastMessageToOtherInstances(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE, payload);
        super.sendNoticeToAll(payload);
    }

    @Override
    public <T> void sendOnlineToAll(T payload) {
        syncBroadcastMessageToOtherInstances(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_ONLINE, payload);
        super.sendOnlineToAll(payload);
    }

    private <T> void syncMessageToOtherInstances(WebSocketMessage<T> webSocketMessage) {
        log.debug("[FUCK] |- Sync MESSAGE to other web socket instance.");
        streamBridge.send(MessageConstants.MULTIPLE_INSTANCE_OUTPUT, webSocketMessage);
    }

    private <T> void syncBroadcastMessageToOtherInstances(String channel, T payload) {
        WebSocketMessage<T> webSocketMessage = new WebSocketMessage<>();
        webSocketMessage.setTo(MessageConstants.MESSAGE_TO_ALL);
        webSocketMessage.setChannel(channel);
        webSocketMessage.setPayload(payload);

        syncMessageToOtherInstances(webSocketMessage);
    }
}
