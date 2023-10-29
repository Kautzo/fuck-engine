package cn.fuck.engine.captcha.autoconfigure;

import cn.fuck.engine.captcha.behavior.renderer.JigsawCaptchaRenderer;
import cn.fuck.engine.captcha.behavior.renderer.WordClickCaptchaRenderer;
import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import cn.fuck.engine.captcha.core.provider.ResourceProvider;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 行为验证码配置 </p>
 * @date : 2022/1/18 20:57
 */
@AutoConfiguration
public class BehaviorCaptchaAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BehaviorCaptchaAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Captcha Behavior] Auto Configure.");
    }

    @Bean(CaptchaCategory.JIGSAW_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public JigsawCaptchaRenderer jigsawCaptchaRenderer(ResourceProvider resourceProvider) {
        JigsawCaptchaRenderer jigsawCaptchaRenderer = new JigsawCaptchaRenderer();
        jigsawCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Jigsaw Captcha Renderer] Auto Configure.");
        return jigsawCaptchaRenderer;
    }

    @Bean(CaptchaCategory.WORD_CLICK_CAPTCHA)
    @ConditionalOnBean(ResourceProvider.class)
    public WordClickCaptchaRenderer wordClickCaptchaRenderer(ResourceProvider resourceProvider) {
        WordClickCaptchaRenderer wordClickCaptchaRenderer = new WordClickCaptchaRenderer();
        wordClickCaptchaRenderer.setResourceProvider(resourceProvider);
        log.trace("[FUCK] |- Bean [Word Click Captcha Renderer] Auto Configure.");
        return wordClickCaptchaRenderer;
    }
}
