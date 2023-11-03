package cn.fuck.engine.oauth2.management.compliance.condition;

import cn.fuck.engine.assistant.core.context.PropertyResolver;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 自动解锁用户账号条件 </p>
 */
@Slf4j
public class AutoUnlockUserAccountCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
        boolean result = PropertyResolver.getBoolean(conditionContext, OAuth2Constants.ITEM_COMPLIANCE_AUTO_UNLOCK, true);
        log.debug("[FUCK] |- Condition [Auto Unlock User Account] value is [{}]", result);
        return result;
    }
}
