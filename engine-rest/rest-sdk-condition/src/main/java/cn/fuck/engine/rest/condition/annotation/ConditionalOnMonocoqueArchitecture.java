package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.MonocoqueArchitectureCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 单体架构模式条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(MonocoqueArchitectureCondition.class)
public @interface ConditionalOnMonocoqueArchitecture {
}
