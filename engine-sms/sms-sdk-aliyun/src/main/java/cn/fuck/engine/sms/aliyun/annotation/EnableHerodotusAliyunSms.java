package cn.fuck.engine.sms.aliyun.annotation;

import cn.fuck.engine.sms.aliyun.configuration.AliyunSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启阿里云短信</p>
 * @date : 2022/1/17 21:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AliyunSmsConfiguration.class)
public @interface EnableHerodotusAliyunSms {
}
