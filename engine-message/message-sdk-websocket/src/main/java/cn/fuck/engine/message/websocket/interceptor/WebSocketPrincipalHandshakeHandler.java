package cn.fuck.engine.message.websocket.interceptor;

import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import cn.fuck.engine.assistant.core.domain.PrincipalDetails;
import cn.fuck.engine.message.websocket.domain.WebSocketPrincipal;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * <p>Description: 设置认证用户信息 </p>
 * @date : 2021/10/24 18:52
 */
public class WebSocketPrincipalHandshakeHandler extends DefaultHandshakeHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketPrincipalHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {

        Object object = attributes.get(BaseConstants.PRINCIPAL);

        if (ObjectUtils.isNotEmpty(object) && object instanceof PrincipalDetails details) {
            WebSocketPrincipal webSocketPrincipal = new WebSocketPrincipal(details);
            log.debug("[FUCK] |- Determine user by request parameter, userId is  [{}].", webSocketPrincipal.getUserId());
            return webSocketPrincipal;
        }

        Principal principal = request.getPrincipal();
        if (ObjectUtils.isNotEmpty(principal)) {
            log.debug("[FUCK] |- Determine user from request, value is  [{}].", principal.getName());
            return principal;
        }

        log.warn("[FUCK] |- Can not determine user from request.");
        return null;
    }
}
