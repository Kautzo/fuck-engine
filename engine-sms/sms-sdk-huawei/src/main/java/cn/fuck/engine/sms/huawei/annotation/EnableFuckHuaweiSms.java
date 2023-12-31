package cn.fuck.engine.sms.huawei.annotation;

import cn.fuck.engine.sms.huawei.configuration.HuaweiSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启华为云短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HuaweiSmsConfiguration.class)
public @interface EnableFuckHuaweiSms {
}
