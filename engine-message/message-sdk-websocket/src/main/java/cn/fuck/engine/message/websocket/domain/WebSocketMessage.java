package cn.fuck.engine.message.websocket.domain;

import java.io.Serializable;

/**
 * <p>Description: WebSocket发送消息参数实体 </p>
 * @date : 2021/10/24 18:42
 */
public class WebSocketMessage<T> implements Serializable {

    private String to;

    private String channel;

    private T payload;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
