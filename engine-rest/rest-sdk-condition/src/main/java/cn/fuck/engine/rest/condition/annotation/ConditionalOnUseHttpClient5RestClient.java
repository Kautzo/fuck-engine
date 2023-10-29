package cn.fuck.engine.rest.condition.annotation;

import cn.fuck.engine.rest.condition.definition.UseHttpClient5RestClientCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 使用 HttpClient 客户端 条件注解 </p>
 * @date : 2023/6/15 21:29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(UseHttpClient5RestClientCondition.class)
public @interface ConditionalOnUseHttpClient5RestClient {
}
