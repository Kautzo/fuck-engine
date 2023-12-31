package cn.fuck.engine.sms.tencent.annotation;

import cn.fuck.engine.sms.tencent.configuration.TencentSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启腾讯云短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TencentSmsConfiguration.class)
public @interface EnableFuckTencentSms {
}
