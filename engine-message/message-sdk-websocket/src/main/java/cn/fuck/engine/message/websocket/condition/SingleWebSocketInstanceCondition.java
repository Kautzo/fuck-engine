package cn.fuck.engine.message.websocket.condition;

import cn.fuck.engine.message.websocket.enums.InstanceMode;
import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.message.core.constants.MessageConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: WebSocket 单实例环境判断条件 </p>
 * @date : 2023/9/14 13:50
 */
public class SingleWebSocketInstanceCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(SingleWebSocketInstanceCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        String property = PropertyResolver.getProperty(conditionContext.getEnvironment(), MessageConstants.ITEM_WEBSOCKET_MULTIPLE_INSTANCE, InstanceMode.SINGLE.name());
        boolean result = StringUtils.equalsIgnoreCase(property, InstanceMode.SINGLE.name());
        log.debug("[FUCK] |- Condition [Single Web Socket Instance] value is [{}]", result);
        return result;
    }
}
