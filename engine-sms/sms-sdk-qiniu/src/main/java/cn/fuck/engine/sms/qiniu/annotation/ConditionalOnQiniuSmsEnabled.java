package cn.fuck.engine.sms.qiniu.annotation;

import cn.fuck.engine.sms.qiniu.condition.QiniuSmsEnabledCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * <p>Description: 七牛云短信开启条件注解 </p>
 * @date : 2022/1/27 16:25
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(QiniuSmsEnabledCondition.class)
public @interface ConditionalOnQiniuSmsEnabled {
}
