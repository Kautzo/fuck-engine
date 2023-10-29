package cn.fuck.engine.captcha.autoconfigure;

import cn.fuck.engine.captcha.autoconfigure.customizer.CaptchaErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.captcha.core.processor.CaptchaRendererFactory;
import cn.fuck.engine.captcha.core.properties.CaptchaProperties;
import cn.fuck.engine.captcha.core.provider.ResourceProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 验证码自动注入 </p>
 * @date : 2022/1/18 21:12
 */
@AutoConfiguration
@EnableConfigurationProperties(CaptchaProperties.class)
public class CaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Captcha Starter] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public ResourceProvider resourceProvider(CaptchaProperties captchaProperties) {
        ResourceProvider resourceProvider = new ResourceProvider(captchaProperties);
        log.trace("[FUCK] |- Bean [Resource Provider] Auto Configure.");
        return resourceProvider;
    }

    @Bean
    @ConditionalOnMissingBean
    public CaptchaRendererFactory captchaRendererFactory() {
        CaptchaRendererFactory captchaRendererFactory = new CaptchaRendererFactory();
        log.trace("[FUCK] |- Bean [Captcha Renderer Factory] Auto Configure.");
        return captchaRendererFactory;
    }

    @Bean
    public ErrorCodeMapperBuilderCustomizer captchaErrorCodeMapperBuilderCustomizer() {
        CaptchaErrorCodeMapperBuilderCustomizer customizer = new CaptchaErrorCodeMapperBuilderCustomizer();
        log.debug("[FUCK] |- Strategy [Captcha ErrorCodeMapper Builder Customizer] Auto Configure.");
        return customizer;
    }
}
