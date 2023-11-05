package cn.fuck.engine.message.websocket.listener;

import cn.fuck.engine.message.websocket.definition.AbstractWebSocketListener;
import cn.fuck.engine.message.websocket.definition.WebSocketMessageSender;
import cn.fuck.engine.cache.redis.utils.RedisBitMapUtils;
import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.message.websocket.domain.WebSocketPrincipal;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * <p>Description: WebSocketUserDisconnectListener </p>
 * <p>
 * 使用监听方式，主要为了将 WebSocket 基本配置，与应用操作解耦。避免产生注入循环。
 */
@Component
public class WebSocketDisconnectListener extends AbstractWebSocketListener<SessionDisconnectEvent> {

    private static final Logger log = LoggerFactory.getLogger(WebSocketDisconnectListener.class);

    public WebSocketDisconnectListener(WebSocketMessageSender webSocketMessageSender) {
        super(webSocketMessageSender);
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        WebSocketPrincipal principal = (WebSocketPrincipal) event.getUser();

        if (ObjectUtils.isNotEmpty(principal)) {

            RedisBitMapUtils.setBit(MessageConstants.REDIS_CURRENT_ONLINE_USER, principal.getName(), false);

            log.debug("[FUCK] |- WebSocket user [{}] Offline.", principal);

            this.syncUserCountToAll();
        }
    }
}
