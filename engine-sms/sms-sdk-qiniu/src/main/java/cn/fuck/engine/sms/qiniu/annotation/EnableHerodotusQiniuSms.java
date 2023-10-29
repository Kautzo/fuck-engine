package cn.fuck.engine.sms.qiniu.annotation;

import cn.fuck.engine.sms.qiniu.configuration.QiniuSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启七牛云短信 </p>
 * @date : 2022/1/17 21:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(QiniuSmsConfiguration.class)
public @interface EnableHerodotusQiniuSms {
}
