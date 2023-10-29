package cn.fuck.engine.sms.chinamobile.annotation;

import cn.fuck.engine.sms.chinamobile.configuration.ChinaMobileSmsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 手动开启移动云短信 </p>
 * @date : 2022/1/17 21:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ChinaMobileSmsConfiguration.class)
public @interface EnableHerodotusChinaMobileSms {
}
