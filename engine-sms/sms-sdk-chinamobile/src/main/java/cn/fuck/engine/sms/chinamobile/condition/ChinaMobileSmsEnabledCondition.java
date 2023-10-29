package cn.fuck.engine.sms.chinamobile.condition;

import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 移动云短信短信开启条件 </p>
 * @date : 2022/1/27 16:23
 */
public class ChinaMobileSmsEnabledCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(ChinaMobileSmsEnabledCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, SmsConstants.ITEM_CHINA_MOBILE_ENABLED);
        log.debug("[FUCK] |- Condition [China Mobile Sms Enabled] value is [{}]", result);
        return result;
    }
}
