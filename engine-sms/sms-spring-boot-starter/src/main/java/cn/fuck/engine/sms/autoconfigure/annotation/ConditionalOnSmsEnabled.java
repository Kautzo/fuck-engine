package cn.fuck.engine.sms.autoconfigure.annotation;

import cn.fuck.engine.sms.autoconfigure.condition.SmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 短信开启条件注解 </p>
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(SmsEnabledCondition.class)
public @interface ConditionalOnSmsEnabled {
}
