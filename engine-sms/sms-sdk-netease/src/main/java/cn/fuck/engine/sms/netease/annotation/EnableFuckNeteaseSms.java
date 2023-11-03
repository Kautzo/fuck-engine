package cn.fuck.engine.sms.netease.annotation;

import cn.fuck.engine.sms.netease.configuration.NeteaseSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启网易短信 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(NeteaseSmsConfiguration.class)
public @interface EnableFuckNeteaseSms {
}
