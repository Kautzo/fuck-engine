package cn.fuck.engine.sms.upyun.condition;

import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 又拍短信开启条件 </p>
 */
@Slf4j
public class UpyunSmsEnabledCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, SmsConstants.ITEM_UPYUN_ENABLED);
        log.debug("[FUCK] |- Condition [Upyun Sms Enabled] value is [{}]", result);
        return result;
    }
}
