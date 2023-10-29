package cn.fuck.engine.sms.tencent.condition;

import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 阿里云短信开启条件 </p>
 * @date : 2022/1/27 16:23
 */
public class TencentSmsEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(TencentSmsEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, SmsConstants.ITEM_TENCENT_ENABLED);
        log.debug("[FUCK] |- Condition [Tencent Sms Enabled] value is [{}]", result);
        return result;
    }
}
