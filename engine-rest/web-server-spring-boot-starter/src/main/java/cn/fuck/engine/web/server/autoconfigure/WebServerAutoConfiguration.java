package cn.fuck.engine.web.server.autoconfigure;

import cn.fuck.engine.web.server.autoconfigure.configuration.UndertowWebServerFactoryCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Web 自动配置 </p>
 */
@AutoConfiguration
@Import({
        UndertowWebServerFactoryCustomizer.class,
})
public class WebServerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WebServerAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Web Server Starter] Auto Configure.");
    }
}
