package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.assistant.core.enums.Architecture;
import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 分布式架构条件 </p>
 */
@Slf4j
public class DistributedArchitectureCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getArchitecture(conditionContext.getEnvironment(), Architecture.DISTRIBUTED.name());
        boolean result = StringUtils.equalsIgnoreCase(property, Architecture.DISTRIBUTED.name());
        log.debug("[FUCK] |- Condition [Distributed Architecture] value is [{}]", result);
        return result;
    }
}
