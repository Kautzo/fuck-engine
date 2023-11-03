package cn.fuck.engine.upms.domain.listener;

import cn.fuck.engine.rest.core.definition.context.AbstractApplicationContextAware;
import cn.fuck.engine.upms.domain.event.SysAttributeChangeEvent;
import cn.fuck.engine.upms.entity.SysAttribute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>Description: SysAttribute实体数据变更监听 </p>
 */
@Slf4j
@Component
public class SysAttributeEntityListener extends AbstractApplicationContextAware {

    public void postUpdate(SysAttribute entity) {
        log.debug("[FUCK] |- [1] SysAttribute entity @PostUpdate activated, value is : [{}]. Trigger SysAttribute change event.", entity.toString());
        publishEvent(new SysAttributeChangeEvent(entity));
    }
}
