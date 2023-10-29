package cn.fuck.engine.message.core.definition;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: 自定义 Application Event 基础类 </p>
 * @date : 2022/2/4 15:14
 */
public class LocalApplicationEvent<T> extends ApplicationEvent {

    private final T data;

    public LocalApplicationEvent(T data) {
        super(data);
        this.data = data;
    }

    public LocalApplicationEvent(T data, Clock clock) {
        super(data, clock);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
