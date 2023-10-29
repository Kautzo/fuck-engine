package cn.fuck.engine.cache.autoconfigure;

import cn.fuck.engine.cache.autoconfigure.customizer.CacheErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Cache 配置 </p>
 */
@Slf4j
@AutoConfiguration
public class CacheAutoConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Cache Starter] Auto Configure.");
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer cacheErrorCodeMapperBuilderCustomizer() {
        CacheErrorCodeMapperBuilderCustomizer customizer = new CacheErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Cache ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
