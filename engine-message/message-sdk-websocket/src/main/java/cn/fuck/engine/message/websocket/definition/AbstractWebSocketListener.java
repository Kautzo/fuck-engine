package cn.fuck.engine.message.websocket.definition;

import cn.fuck.engine.message.websocket.utils.WebSocketUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: 公共 WebSocketUserListener </p>
 */
public abstract class AbstractWebSocketListener<E extends ApplicationEvent> implements ApplicationListener<E> {

    private final WebSocketMessageSender webSocketMessageSender;

    public AbstractWebSocketListener(WebSocketMessageSender webSocketMessageSender) {
        this.webSocketMessageSender = webSocketMessageSender;
    }

    protected void syncUserCountToAll() {
        int count = WebSocketUtils.getOnlineCount();
        webSocketMessageSender.sendOnlineToAll(count);
    }
}
