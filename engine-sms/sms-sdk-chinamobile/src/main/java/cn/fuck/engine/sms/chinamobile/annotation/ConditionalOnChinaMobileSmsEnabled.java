package cn.fuck.engine.sms.chinamobile.annotation;

import cn.fuck.engine.sms.chinamobile.condition.ChinaMobileSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 移动云短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ChinaMobileSmsEnabledCondition.class)
public @interface ConditionalOnChinaMobileSmsEnabled {
}
