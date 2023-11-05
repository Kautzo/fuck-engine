package cn.fuck.engine.message.autoconfigure;

import cn.fuck.engine.message.autoconfigure.customizer.MessageErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Message 模块自动注入配置 </p>
 */
@AutoConfiguration
public class MessageAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MessageAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Message Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer messageErrorCodeMapperBuilderCustomizer() {
        MessageErrorCodeMapperBuilderCustomizer customizer = new MessageErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Message ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
