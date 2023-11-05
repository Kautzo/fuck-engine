package cn.fuck.engine.message.security.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;

/**
 * <p>Description: Security Event 自动配置 </p>
 */
@AutoConfiguration
@RemoteApplicationEventScan({
        "cn.fuck.engine.message.security.autoconfigure.event"
})
public class MessageSecurityAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageSecurityAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Message Security Starter] Auto Configure.");
    }
}
