package cn.fuck.engine.message.websocket.definition;

import cn.fuck.engine.message.core.exception.IllegalChannelException;
import cn.fuck.engine.message.core.exception.PrincipalNotFoundException;
import cn.fuck.engine.message.websocket.domain.WebSocketMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;

/**
 * <p>Description: WebSocketMessageSender 抽象实现 </p>
 * @date : 2023/9/14 17:22
 */
public abstract class AbstractWebSocketMessageSender implements WebSocketMessageSender {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final SimpUserRegistry simpUserRegistry;

    protected AbstractWebSocketMessageSender(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    @Override
    public <T> void toAll(String channel, T payload) {
        simpMessagingTemplate.convertAndSend(channel, payload);
    }

    /**
     * 发送给指定用户信息。
     *
     * @param webSocketMessage 发送内容参数实体 {@link WebSocketMessage}
     * @param <T>              指定 payload 类型
     * @throws IllegalChannelException    Web Socket 通道设置错误
     * @throws PrincipalNotFoundException 该服务中无法找到与 identity 对应的用户 Principal
     */
    public <T> void toUser(WebSocketMessage<T> webSocketMessage) throws IllegalChannelException, PrincipalNotFoundException {
        simpMessagingTemplate.convertAndSendToUser(webSocketMessage.getTo(), webSocketMessage.getChannel(), webSocketMessage.getPayload());
    }

    @Override
    public SimpUser getUser(String userId) {
        return simpUserRegistry.getUser(userId);
    }

    @Override
    public SimpMessagingTemplate getSimpMessagingTemplate() {
        return simpMessagingTemplate;
    }
}
