package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.RemoteDataAccessCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 远程数据访问策略条件注解 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(RemoteDataAccessCondition.class)
public @interface ConditionalOnRemoteDataAccess {
}
