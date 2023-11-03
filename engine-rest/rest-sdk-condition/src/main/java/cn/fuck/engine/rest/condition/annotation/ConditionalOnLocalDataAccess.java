package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.LocalDataAccessCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 本地数据访问策略条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(LocalDataAccessCondition.class)
public @interface ConditionalOnLocalDataAccess {
}
