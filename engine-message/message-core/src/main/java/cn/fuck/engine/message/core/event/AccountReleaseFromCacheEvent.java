package cn.fuck.engine.message.core.event;

import cn.fuck.engine.message.core.definition.LocalApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 从账户状态缓存中释放账号事件 </p>
 * @date : 2023/5/14 14:26
 */
public class AccountReleaseFromCacheEvent extends LocalApplicationEvent<String> {

    public AccountReleaseFromCacheEvent(String data) {
        super(data);
    }

    public AccountReleaseFromCacheEvent(String data, Clock clock) {
        super(data, clock);
    }
}
