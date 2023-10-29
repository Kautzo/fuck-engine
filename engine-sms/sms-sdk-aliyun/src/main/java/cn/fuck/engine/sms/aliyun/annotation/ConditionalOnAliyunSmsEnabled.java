package cn.fuck.engine.sms.aliyun.annotation;

import cn.fuck.engine.sms.aliyun.condition.AliyunSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 阿里云短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(AliyunSmsEnabledCondition.class)
public @interface ConditionalOnAliyunSmsEnabled {
}
