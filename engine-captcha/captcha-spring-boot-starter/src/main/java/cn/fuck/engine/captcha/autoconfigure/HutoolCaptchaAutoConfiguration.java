package cn.fuck.engine.captcha.autoconfigure;

import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.fuck.engine.captcha.core.provider.ResourceProvider;
import cn.fuck.engine.captcha.hutool.renderer.CircleCaptchaRenderer;
import cn.fuck.engine.captcha.hutool.renderer.GifCaptchaRenderer;
import cn.fuck.engine.captcha.hutool.renderer.LineCaptchaRenderer;
import cn.fuck.engine.captcha.hutool.renderer.ShearCaptchaRenderer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Hutool 验证码配置 </p>
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration
public class HutoolCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HutoolCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Captcha Hutool] Auto Configure.");
    }

    @Bean(CaptchaCategory.HUTOOL_LINE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public LineCaptchaRenderer lineCaptchaRenderer(ResourceProvider resourceProvider) {
        LineCaptchaRenderer lineCaptchaRenderer = new LineCaptchaRenderer();
        lineCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Hutool Line Captcha Renderer] Auto Configure.");
        return lineCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_CIRCLE_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public CircleCaptchaRenderer circleCaptchaRenderer(ResourceProvider resourceProvider) {
        CircleCaptchaRenderer circleCaptchaRenderer = new CircleCaptchaRenderer();
        circleCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Hutool Circle Captcha Renderer] Auto Configure.");
        return circleCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_SHEAR_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public ShearCaptchaRenderer shearCaptchaRenderer(ResourceProvider resourceProvider) {
        ShearCaptchaRenderer shearCaptchaRenderer = new ShearCaptchaRenderer();
        shearCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Hutool Shear Captcha Renderer] Auto Configure.");
        return shearCaptchaRenderer;
    }

    @Bean(CaptchaCategory.HUTOOL_GIF_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public GifCaptchaRenderer gifCaptchaRenderer(ResourceProvider resourceProvider) {
        GifCaptchaRenderer gifCaptchaRenderer = new GifCaptchaRenderer();
        gifCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Hutool Gif Captcha Renderer] Auto Configure.");
        return gifCaptchaRenderer;
    }
}
