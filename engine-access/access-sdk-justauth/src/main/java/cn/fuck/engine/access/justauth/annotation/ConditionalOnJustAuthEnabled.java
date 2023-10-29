package cn.fuck.engine.access.justauth.annotation;

import cn.fuck.engine.access.justauth.condition.JustAuthEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: JustAuth开启条件注解 </p>
 * @date : 2022/1/24 14:40
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(JustAuthEnabledCondition.class)
public @interface ConditionalOnJustAuthEnabled {
}
