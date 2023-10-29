package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.RemoteDataAccessCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 远程数据访问策略条件注解 </p>
 * @date : 2021/8/6 21:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(RemoteDataAccessCondition.class)
public @interface ConditionalOnRemoteDataAccess {
}