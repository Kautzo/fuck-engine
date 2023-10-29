package cn.fuck.engine.captcha.graphic.renderer;

import cn.fuck.engine.captcha.graphic.definition.AbstractPngGraphicRenderer;
import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 类型验证码绘制器 </p>
 * @date : 2021/12/20 20:39
 */
@Component
public class SpecCaptchaRenderer extends AbstractPngGraphicRenderer {

    @Override
    public String getCategory() {
        return CaptchaCategory.SPEC.getConstant();
    }

    @Override
    protected String[] getDrawCharacters() {
        return this.getCharCharacters();
    }
}
