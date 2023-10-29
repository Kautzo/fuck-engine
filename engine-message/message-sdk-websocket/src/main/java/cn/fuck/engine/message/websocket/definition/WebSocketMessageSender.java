package cn.fuck.engine.message.websocket.definition;

import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.message.core.exception.IllegalChannelException;
import cn.fuck.engine.message.core.exception.PrincipalNotFoundException;
import cn.fuck.engine.message.websocket.domain.WebSocketMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;

/**
 * <p>Description: WebSocket 消息发送器 </p>
 * @date : 2023/9/14 16:34
 */
public interface WebSocketMessageSender {

    /**
     * 发送给指定用户信息
     *
     * @param webSocketMessage 发送内容参数实体 {@link WebSocketMessage}
     * @param <T>              payload 内容对象类型
     * @throws IllegalChannelException    Web Socket 通道设置错误
     * @throws PrincipalNotFoundException 该服务中无法找到与 identity 对应的用户 Principal
     */
    <T> void toUser(WebSocketMessage<T> webSocketMessage) throws IllegalChannelException, PrincipalNotFoundException;

    /**
     * 发送全员消息
     *
     * @param channel 消息通道
     * @param payload 内容
     * @param <T>     payload 内容对象类型
     */
    <T> void toAll(String channel, T payload);

    /**
     * 广播 WebSocket 信息
     *
     * @param payload 发送的内容
     * @param <T>     payload 内容对象类型
     */
    default <T> void sendNoticeToAll(T payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_NOTICE, payload);
    }

    /**
     * 广播 WebSocket 信息
     *
     * @param payload 发送的内容
     * @param <T>     payload 内容对象类型
     */
    default <T> void sendOnlineToAll(T payload) {
        toAll(MessageConstants.WEBSOCKET_DESTINATION_BROADCAST_ONLINE, payload);
    }

    /**
     * 根据用户ID获取 WebSocket 用户
     *
     * @param userId 用户ID
     * @return {@link SimpUser}
     */
    SimpUser getUser(String userId);

    /**
     * 判断用户是否存在
     *
     * @param userId 用户ID
     * @return true 用户存在，false 用户不存在
     */
    default boolean isUserExist(String userId) {
        return ObjectUtils.isNotEmpty(getUser(userId));
    }

    /**
     * 获取到 SimpMessagingTemplate
     *
     * @return {@link SimpMessagingTemplate}
     */
    SimpMessagingTemplate getSimpMessagingTemplate();
}
