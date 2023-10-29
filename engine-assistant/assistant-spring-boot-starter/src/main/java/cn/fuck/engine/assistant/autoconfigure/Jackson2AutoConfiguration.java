package cn.fuck.engine.assistant.autoconfigure;

import cn.fuck.engine.assistant.autoconfigure.customizer.Jackson2DefaultObjectMapperBuilderCustomizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * <p>Description: Jackson2 配置 </p>
 * @date : 2022/6/1 0:09
 */
@AutoConfiguration
public class Jackson2AutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(Jackson2AutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Jackson2] Auto Configure.");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer defaultObjectMapperBuilderCustomizer() {
        Jackson2DefaultObjectMapperBuilderCustomizer customizer = new Jackson2DefaultObjectMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Jackson2 Default ObjectMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    /**
     * 转换器全局配置
     *
     * @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        log.trace("[FUCK] |- Bean [Jackson2 Http Message Converter] Auto Configure.");
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Configuration(proxyBeanMethods = false)
    @ComponentScan({
            "cn.fuck.engine.assistant.core.json.jackson2.utils"
    })
    static class JacksonUtilsConfiguration {

        @PostConstruct
        public void postConstruct() {
            log.debug("[FUCK] |- SDK [Assistant Jackson2 Utils] Auto Configure.");
        }
    }
}
