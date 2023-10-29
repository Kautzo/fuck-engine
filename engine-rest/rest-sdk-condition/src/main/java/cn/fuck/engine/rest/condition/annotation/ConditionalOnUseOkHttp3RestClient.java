package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.UseOkHttp3RestClientCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 使用 OkHttp 客户端 条件注解 </p>
 * @date : 2023/6/15 21:29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(UseOkHttp3RestClientCondition.class)
public @interface ConditionalOnUseOkHttp3RestClient {
}
