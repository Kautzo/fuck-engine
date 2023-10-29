package cn.fuck.engine.assistant.ip2region.annotation;

import cn.fuck.engine.assistant.ip2region.configuration.Ip2RegionConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>Description: 开启 Ip2Region 配置注解 </p>
 * @date : 2023/10/25 17:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(Ip2RegionConfiguration.class)
public @interface EnableHerodotusIp2Region {
}
