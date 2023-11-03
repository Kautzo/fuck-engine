package cn.fuck.engine.upms.domain.event;

import cn.fuck.engine.message.core.definition.LocalApplicationEvent;
import cn.fuck.engine.upms.entity.SysAttribute;

import java.time.Clock;

/**
 * <p>Description: SysSecurityAttribute实体数据变更事件 </p>
 */
public class SysAttributeChangeEvent extends LocalApplicationEvent<SysAttribute> {

    public SysAttributeChangeEvent(SysAttribute data) {
        super(data);
    }

    public SysAttributeChangeEvent(SysAttribute data, Clock clock) {
        super(data, clock);
    }
}
