package cn.fuck.engine.upms.configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * <p>Description: UpmsRest配置类 </p>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {"cn.fuck.engine.upms"})
public class UpmsConfiguration {


    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- SDK [Upms] Auto Configure.");
    }


}
