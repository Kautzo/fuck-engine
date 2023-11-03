package cn.fuck.engine.rest.autoconfigure;

import cn.fuck.engine.rest.service.configuration.FeignConfiguration;
import cn.fuck.engine.rest.service.configuration.RestScanConfiguration;
import cn.fuck.engine.rest.service.configuration.SpringdocConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Rest 服务基础内容配置 </p>
 */
@Slf4j
@AutoConfiguration
@Import({
        FeignConfiguration.class,
        RestScanConfiguration.class,
        SpringdocConfiguration.class
})
public class RestServiceAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Rest Service] Auto Configure.");
    }
}
