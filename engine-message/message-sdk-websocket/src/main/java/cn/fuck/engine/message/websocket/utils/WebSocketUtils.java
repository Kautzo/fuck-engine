package cn.fuck.engine.message.websocket.utils;

import cn.fuck.engine.cache.redis.utils.RedisBitMapUtils;
import cn.fuck.engine.message.core.constants.MessageConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;

/**
 * <p>Description: WebSocket 通用工具类 </p>
 */
public class WebSocketUtils {

    public static HttpServletRequest getHttpServletRequest(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest request) {
            return request.getServletRequest();
        }

        return null;
    }

    public static HttpServletResponse getHttpServletResponse(ServerHttpResponse serverHttpResponse) {
        if (serverHttpResponse instanceof ServletServerHttpResponse response) {
            return response.getServletResponse();
        }

        return null;
    }

    public static int getOnlineCount() {
        Long count = RedisBitMapUtils.bitCount(MessageConstants.REDIS_CURRENT_ONLINE_USER);
        return count.intValue();
    }
}
