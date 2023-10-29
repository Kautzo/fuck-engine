package cn.fuck.engine.sms.upyun.annotation;

import cn.fuck.engine.sms.upyun.configuration.UpyunSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启又拍短信 </p>
 * @date : 2022/1/17 21:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UpyunSmsConfiguration.class)
public @interface EnableHerodotusUpyunSms {
}
