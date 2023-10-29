package cn.fuck.engine.rest.autoconfigure;

import cn.fuck.engine.rest.service.configuration.FeignConfiguration;
import cn.fuck.engine.rest.service.configuration.RestScanConfiguration;
import cn.fuck.engine.rest.service.configuration.SpringdocConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Rest 服务基础内容配置 </p>
 * @date : 2023/10/3 22:55
 */
@AutoConfiguration
@Import({
        FeignConfiguration.class,
        RestScanConfiguration.class,
        SpringdocConfiguration.class
})
public class RestServiceAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RestServiceAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Rest Service] Auto Configure.");
    }
}
