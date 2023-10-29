package cn.fuck.engine.assistant.core.annotation;

import cn.fuck.engine.assistant.core.conditon.SwaggerEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: Swagger条件开启主机 </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(SwaggerEnabledCondition.class)
public @interface ConditionalOnSwaggerEnabled {
}
