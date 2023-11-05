package cn.fuck.engine.message.websocket.processor;

import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.message.websocket.definition.WebSocketMessageSender;
import cn.fuck.engine.message.websocket.domain.WebSocketMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * <p>Description: WebSocket 点对点消息跨实例处理 </p>
 */
public class MultipleInstanceMessageSyncConsumer<T> implements Consumer<WebSocketMessage<T>> {

    private static final Logger log = LoggerFactory.getLogger(MultipleInstanceMessageSyncConsumer.class);

    private final WebSocketMessageSender webSocketMessageSender;

    public MultipleInstanceMessageSyncConsumer(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    @Override
    public void accept(WebSocketMessage<T> webSocketMessage) {
        if (StringUtils.equals(webSocketMessage.getTo(), MessageConstants.MESSAGE_TO_ALL)) {
            if (StringUtils.equals(webSocketMessage.getChannel(), MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE)) {
                webSocketMessageSender.sendNoticeToAll(webSocketMessage.getPayload());
            } else {
                webSocketMessageSender.sendOnlineToAll(webSocketMessage.getPayload());
            }
        } else {
            // 如果当前 WebSocket 实例用，可以找到该用户就 WebSocket 消息
            if (webSocketMessageSender.isUserExist(webSocketMessage.getTo())) {
                log.debug("[FUCK] |- Web socket send message to user [{}].", webSocketMessage.getTo());
                // 注意：这里不要直接使用 webSocketMessageSender 中的 toUser 方法。这里会注入 MultipleInstanceMessageSender。
                // MultipleInstanceMessageSender 中的 toUser 方法中包含多实例同步消息的发送。如果调用了这个方法，发现用户不再该实例中，就会发送同步消息，存在循环发送消息的风险。
                // 正常情况下不会出现消息循环，就怕不小心改错了
                webSocketMessageSender.getSimpMessagingTemplate().convertAndSendToUser(webSocketMessage.getTo(), webSocketMessage.getChannel(), webSocketMessage.getPayload());
            }

            log.warn("[FUCK] |- Current web socket instance can not found user [{}].", webSocketMessage.getTo());
        }

    }
}
