package cn.fuck.engine.captcha.graphic.renderer;

import cn.fuck.engine.captcha.graphic.definition.AbstractGifGraphicRenderer;
import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Gif 类型验证码绘制器 </p>
 * @date : 2021/12/20 22:54
 */
@Component
public class SpecGifCaptchaRenderer extends AbstractGifGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.SPEC_GIF.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getCharCharacters();
    }
}
