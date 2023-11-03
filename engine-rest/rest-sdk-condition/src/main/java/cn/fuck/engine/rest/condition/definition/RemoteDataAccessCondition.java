package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.assistant.core.enums.Target;
import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 远程Feign数据访问策略条件定义 </p>
 */
@Slf4j
public class RemoteDataAccessCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getDataAccessStrategy(conditionContext.getEnvironment(), Target.REMOTE.name());
        boolean result = StringUtils.isNotBlank(property) && StringUtils.equalsIgnoreCase(property, Target.REMOTE.name());
        log.debug("[FUCK] |- Condition [Remote Data Access] value is [{}]", result);
        return result;
    }
}
