package cn.fuck.engine.supplier.upms.logic.domain.event;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysAttribute;
import cn.fuck.engine.message.core.definition.LocalApplicationEvent;

import java.time.Clock;

/**
 * <p>Description: SysSecurityAttribute实体数据变更事件 </p>
 * @date : 2021/8/4 22:18
 */
public class SysAttributeChangeEvent extends LocalApplicationEvent<SysAttribute> {

    public SysAttributeChangeEvent(SysAttribute data) {
        super(data);
    }

    public SysAttributeChangeEvent(SysAttribute data, Clock clock) {
        super(data, clock);
    }
}
