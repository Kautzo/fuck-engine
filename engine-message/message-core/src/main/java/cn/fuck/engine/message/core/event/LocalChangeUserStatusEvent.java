package cn.fuck.engine.message.core.event;

import cn.fuck.engine.message.core.definition.LocalApplicationEvent;
import cn.fuck.engine.message.core.domain.UserStatus;

import java.time.Clock;

/**
 * <p>Description: 本地用户状态变更事件 </p>
 * @date : 2022/7/10 16:15
 */
public class LocalChangeUserStatusEvent extends LocalApplicationEvent<UserStatus> {

    public LocalChangeUserStatusEvent(UserStatus data) {
        super(data);
    }

    public LocalChangeUserStatusEvent(UserStatus data, Clock clock) {
        super(data, clock);
    }
}
