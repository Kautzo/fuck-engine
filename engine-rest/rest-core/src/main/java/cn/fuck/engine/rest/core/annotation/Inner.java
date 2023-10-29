package cn.fuck.engine.rest.core.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: Feign 内部调用标记注解 </p>
 * @date : 2022/5/31 12:10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

}