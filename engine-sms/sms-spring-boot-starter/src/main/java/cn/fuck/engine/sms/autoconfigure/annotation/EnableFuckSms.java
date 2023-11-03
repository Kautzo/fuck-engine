package cn.fuck.engine.sms.autoconfigure.annotation;

import cn.fuck.engine.cache.jetcache.annotation.EnableFuckJetCache;
import cn.fuck.engine.sms.autoconfigure.SmsAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启短信统一支持 </p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFuckJetCache
@Import(SmsAutoConfiguration.class)
public @interface EnableFuckSms {
}
