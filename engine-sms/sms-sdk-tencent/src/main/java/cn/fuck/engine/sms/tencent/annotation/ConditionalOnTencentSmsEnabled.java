package cn.fuck.engine.sms.tencent.annotation;

import cn.fuck.engine.sms.tencent.condition.TencentSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 腾讯云短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(TencentSmsEnabledCondition.class)
public @interface ConditionalOnTencentSmsEnabled {
}
