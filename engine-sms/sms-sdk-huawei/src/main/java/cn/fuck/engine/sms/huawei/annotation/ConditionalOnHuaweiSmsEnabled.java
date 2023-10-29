package cn.fuck.engine.sms.huawei.annotation;

import cn.fuck.engine.sms.huawei.condition.HuaweiSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 华为云短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(HuaweiSmsEnabledCondition.class)
public @interface ConditionalOnHuaweiSmsEnabled {
}
