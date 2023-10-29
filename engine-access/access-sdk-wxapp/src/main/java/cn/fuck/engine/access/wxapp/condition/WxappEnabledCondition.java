package cn.fuck.engine.access.wxapp.condition;

import cn.fuck.engine.access.core.constants.AccessConstants;
import cn.fuck.engine.assistant.core.context.PropertyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 微信小程序注入条件 </p>
 * @date : 2021/5/27 22:13
 */
public class WxappEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(WxappEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, AccessConstants.ITEM_WXAPP_ENABLED);
        log.debug("[FUCK] |- Condition [Wxapp Enabled] value is [{}]", result);
        return result;
    }
}
