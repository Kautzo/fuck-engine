package cn.fuck.engine.message.websocket.processor;

import cn.fuck.engine.message.websocket.definition.AbstractWebSocketMessageSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;

/**
 * <p>Description: Web Socket 单一实例服务端消息发送 </p>
 */
public class SingleInstanceMessageSender extends AbstractWebSocketMessageSender {

    public SingleInstanceMessageSender(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
        super(simpMessagingTemplate, simpUserRegistry);
    }
}
