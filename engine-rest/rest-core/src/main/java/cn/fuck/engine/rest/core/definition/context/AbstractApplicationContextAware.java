package cn.fuck.engine.rest.core.definition.context;

import cn.fuck.engine.assistant.core.context.ServiceContextHolder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * <p>Description: 抽象实体变更 Listener</p>
 */
public abstract class AbstractApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected ApplicationContext getApplicationContext() {
        if (ObjectUtils.isEmpty(applicationContext)) {
            return ServiceContextHolder.getInstance().getApplicationContext();
        }
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected void publishEvent(ApplicationEvent event) {
        this.getApplicationContext().publishEvent(event);
    }
}
