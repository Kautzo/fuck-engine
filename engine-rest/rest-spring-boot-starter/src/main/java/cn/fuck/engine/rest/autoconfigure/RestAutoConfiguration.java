package cn.fuck.engine.rest.autoconfigure;

import cn.fuck.engine.rest.autoconfigure.customizer.Jackson2XssObjectMapperBuilderCustomizer;
import cn.fuck.engine.rest.autoconfigure.customizer.RestErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.context.ServiceContextHolder;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.rest.service.initializer.ServiceContextHolderBuilder;
import cn.fuck.engine.rest.condition.properties.EndpointProperties;
import cn.fuck.engine.rest.condition.properties.PlatformProperties;
import cn.fuck.engine.rest.condition.constants.RestPropertyFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Rest 自动注入 </p>
 *
 * {@code RestAutoConfiguration} 注入的时机比较早，所以在此进行 {@link ServiceContextHolder} 的初始化。
 * 如果在其它地方注入 {@link ServiceContextHolder} 因为时机过完，会导致未完成初始化即被使用，出现抛错的问题。
 * @date : 2022/1/19 23:16
 */
@AutoConfiguration
@EnableConfigurationProperties({EndpointProperties.class, PlatformProperties.class})
public class RestAutoConfiguration implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(RestAutoConfiguration.class);

    private final ServiceContextHolder serviceContextHolder;

    public RestAutoConfiguration(EndpointProperties endpointProperties, PlatformProperties platformProperties, ServerProperties serverProperties) {
        this.serviceContextHolder = ServiceContextHolderBuilder.builder()
                .endpointProperties(endpointProperties)
                .platformProperties(platformProperties)
                .serverProperties(serverProperties)
                .build();
        log.info("[FUCK] |- Module [Rest Starter] Auto Configure.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.serviceContextHolder.setApplicationContext(applicationContext);
        this.serviceContextHolder.setApplicationName(RestPropertyFinder.getApplicationName(applicationContext.getEnvironment()));
        log.debug("[FUCK] |- HERODOTUS ApplicationContext initialization completed.");
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer xssObjectMapperBuilderCustomizer() {
        Jackson2XssObjectMapperBuilderCustomizer customizer = new Jackson2XssObjectMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Jackson2 Xss ObjectMapper Builder Customizer] Auto Configure.");
        return customizer;
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer restErrorCodeMapperBuilderCustomizer() {
        RestErrorCodeMapperBuilderCustomizer customizer = new RestErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Rest ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
