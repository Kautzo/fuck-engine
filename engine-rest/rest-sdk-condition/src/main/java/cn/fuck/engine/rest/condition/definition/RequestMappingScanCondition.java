package cn.fuck.engine.rest.condition.definition;

import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * <p>Description: Request Mapping 扫描条件 </p>
 */
@Slf4j
public class RequestMappingScanCondition implements Condition {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean result = RestPropertyFinder.isScanEnabled(conditionContext.getEnvironment());
        log.debug("[FUCK] |- Condition [Request Mapping Scan] value is [{}]", result);
        return result;
    }
}
