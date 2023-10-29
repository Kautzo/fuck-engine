package cn.fuck.engine.supplier.upms.logic.domain.listener;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysAttribute;
import cn.fuck.engine.rest.core.definition.context.AbstractApplicationContextAware;
import cn.fuck.engine.supplier.upms.logic.domain.event.SysAttributeChangeEvent;
import jakarta.persistence.PostUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description: SysAttribute实体数据变更监听 </p>
 * @date : 2021/8/4 16:54
 */
public class SysAttributeEntityListener extends AbstractApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SysAttributeEntityListener.class);

    @PostUpdate
    protected void postUpdate(SysAttribute entity) {
        log.debug("[FUCK] |- [1] SysAttribute entity @PostUpdate activated, value is : [{}]. Trigger SysAttribute change event.", entity.toString());
        publishEvent(new SysAttributeChangeEvent(entity));
    }
}
