package cn.fuck.engine.captcha.autoconfigure;

import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.fuck.engine.captcha.core.provider.ResourceProvider;
import cn.fuck.engine.captcha.graphic.renderer.*;
import cn.fuck.engine.captcha.graphic.renderer.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 图形验证码配置 </p>
 * @date : 2022/1/18 20:56
 */
@AutoConfiguration
public class GraphicCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GraphicCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Captcha Graphic] Auto Configure.");
    }

    @Bean(CaptchaCategory.ARITHMETIC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ArithmeticCaptchaRenderer arithmeticCaptchaRenderer(ResourceProvider resourceProvider) {
        ArithmeticCaptchaRenderer arithmeticCaptchaRenderer = new ArithmeticCaptchaRenderer();
        arithmeticCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Arithmetic Captcha Renderer] Auto Configure.");
        return arithmeticCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ChineseCaptchaRenderer chineseCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseCaptchaRenderer chineseCaptchaRenderer = new ChineseCaptchaRenderer();
        chineseCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Chinese Captcha Renderer] Auto Configure.");
        return chineseCaptchaRenderer;
    }

    @Bean(CaptchaCategory.CHINESE_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ChineseGifCaptchaRenderer chineseGifCaptchaRenderer(ResourceProvider resourceProvider) {
        ChineseGifCaptchaRenderer chineseGifCaptchaRenderer = new ChineseGifCaptchaRenderer();
        chineseGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Chinese Gif Captcha Renderer] Auto Configure.");
        return chineseGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public SpecGifCaptchaRenderer specGifCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecGifCaptchaRenderer specGifCaptchaRenderer = new SpecGifCaptchaRenderer();
        specGifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Spec Gif Captcha Renderer] Auto Configure.");
        return specGifCaptchaRenderer;
    }

    @Bean(CaptchaCategory.SPEC_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public SpecCaptchaRenderer specCaptchaRenderer(ResourceProvider resourceProvider) {
        SpecCaptchaRenderer specCaptchaRenderer = new SpecCaptchaRenderer();
        specCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Spec Captcha Renderer] Auto Configure.");
        return specCaptchaRenderer;
    }
}
