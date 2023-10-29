package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.assistant.core.enums.Architecture;
import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: 分布式架构条件 </p>
 * @date : 2022/1/10 14:51
 */
public class DistributedArchitectureCondition implements Condition {

    private static final Logger log = LoggerFactory.getLogger(DistributedArchitectureCondition.class);

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = RestPropertyFinder.getArchitecture(conditionContext.getEnvironment(), Architecture.DISTRIBUTED.name());
        boolean result = StringUtils.equalsIgnoreCase(property, Architecture.DISTRIBUTED.name());
        log.debug("[FUCK] |- Condition [Distributed Architecture] value is [{}]", result);
        return result;
    }
}
