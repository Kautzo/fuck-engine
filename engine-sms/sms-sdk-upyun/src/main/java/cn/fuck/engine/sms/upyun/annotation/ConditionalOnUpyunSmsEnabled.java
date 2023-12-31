package cn.fuck.engine.sms.upyun.annotation;

import cn.fuck.engine.sms.upyun.condition.UpyunSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 又拍短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(UpyunSmsEnabledCondition.class)
public @interface ConditionalOnUpyunSmsEnabled {
}
