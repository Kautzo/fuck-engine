package cn.fuck.engine.message.core.event;

import cn.fuck.engine.message.core.definition.LocalApplicationEvent;
import cn.fuck.engine.message.core.domain.RequestMapping;

import java.time.Clock;
import java.util.List;

/**
 * <p>Description: 本地RequestMapping收集事件 </p>
 * @date : 2021/8/8 21:55
 */
public class LocalRequestMappingGatherEvent extends LocalApplicationEvent<List<RequestMapping>> {

    public LocalRequestMappingGatherEvent(List<RequestMapping> data) {
        super(data);
    }

    public LocalRequestMappingGatherEvent(List<RequestMapping> data, Clock clock) {
        super(data, clock);
    }
}
