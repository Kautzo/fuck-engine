package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.assistant.core.enums.Target;
import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 本地数据访问策略条件定义 </p>
 */
@Slf4j
public class LocalDataAccessCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getDataAccessStrategy(conditionContext.getEnvironment());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, Target.LOCAL.name());
        log.debug("[FUCK] |- Condition [Local Data Access] value is [{}]", result);
        return result;
    }
}
