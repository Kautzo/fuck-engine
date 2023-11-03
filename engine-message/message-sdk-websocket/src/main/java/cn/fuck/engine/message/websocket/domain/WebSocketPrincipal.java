package cn.fuck.engine.message.websocket.domain;

import cn.fuck.engine.assistant.core.domain.PrincipalDetails;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;

import java.security.Principal;

/**
 * <p>Description: Websocket登录连接对象 </p>
 * <p>
 * 用于保存websocket连接过程中需要存储的业务参数
 */
@Data
public class WebSocketPrincipal implements Principal {

    private String userId;
    private String userName;

    public WebSocketPrincipal(PrincipalDetails details) {
        this.userId = details.getOpenId();
        this.userName = details.getUserName();
    }

    public WebSocketPrincipal(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 这里的 Name 是发送信息目标的标识。
     * <p>
     * 使用 UserName 可控度不高，使用也不方便。直接用ID
     *
     * @return WebSocket 用户的唯一标识
     */
    @Override
    public String getName() {
        return this.userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocketPrincipal that = (WebSocketPrincipal) o;
        return Objects.equal(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("userName", userName)
                .toString();
    }
}
