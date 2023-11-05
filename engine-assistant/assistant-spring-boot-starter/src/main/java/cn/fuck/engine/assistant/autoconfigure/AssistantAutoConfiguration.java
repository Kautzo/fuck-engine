package cn.fuck.engine.assistant.autoconfigure;

import cn.fuck.engine.assistant.autoconfigure.customizer.StandardErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.domain.ErrorCodeMapper;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import jakarta.annotation.PostConstruct;
import org.dromara.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * <p>Description: Definition 自动配置 </p>
 */
@AutoConfiguration
@Import({
        SpringUtil.class,
})
public class AssistantAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AssistantAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Assistant] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer standardErrorCodeMapperBuilderCustomizer() {
        StandardErrorCodeMapperBuilderCustomizer customizer = new StandardErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Standard ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilder errorCodeMapperBuilder(List<ErrorCodeMapperBuilderCustomizer> customizers) {
        ErrorCodeMapperBuilder builder = new ErrorCodeMapperBuilder();
        customize(builder, customizers);
        log.debug("[FUCK] |- Bean [ErrorCodeMapper Builder] Auto Configure.");
        return builder;
    }

    private void customize(ErrorCodeMapperBuilder builder, List<ErrorCodeMapperBuilderCustomizer> customizers) {
        for (ErrorCodeMapperBuilderCustomizer customizer : customizers) {
            customizer.customize(builder);
        }
    }

    @Bean
    public ErrorCodeMapper errorCodeMapper(ErrorCodeMapperBuilder builder) {
        ErrorCodeMapper mapper = builder.build();
        log.debug("[FUCK] |- Bean [ErrorCodeMapper] Auto Configure.");
        return mapper;
    }
}
