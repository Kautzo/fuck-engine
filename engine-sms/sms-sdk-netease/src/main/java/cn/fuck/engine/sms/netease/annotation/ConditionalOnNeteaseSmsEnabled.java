package cn.fuck.engine.sms.netease.annotation;

import cn.fuck.engine.sms.netease.condition.NeteaseSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 网易短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(NeteaseSmsEnabledCondition.class)
public @interface ConditionalOnNeteaseSmsEnabled {
}
