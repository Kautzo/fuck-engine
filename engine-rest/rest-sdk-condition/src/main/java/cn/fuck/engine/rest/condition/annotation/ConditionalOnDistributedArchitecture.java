package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.DistributedArchitectureCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 分布式架构模式条件注解 </p>
 * @date : 2022/1/10 14:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(DistributedArchitectureCondition.class)
public @interface ConditionalOnDistributedArchitecture {
}
