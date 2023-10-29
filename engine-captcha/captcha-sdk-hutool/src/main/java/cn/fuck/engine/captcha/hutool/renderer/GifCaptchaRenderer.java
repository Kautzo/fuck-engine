package cn.fuck.engine.captcha.hutool.renderer;

import cn.fuck.engine.captcha.core.definition.AbstractGraphicRenderer;
import cn.fuck.engine.captcha.core.definition.domain.Metadata;
import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import org.dromara.hutool.swing.captcha.CaptchaUtil;
import org.dromara.hutool.swing.captcha.GifCaptcha;

/**
 * <p>Description: Hutool GIF验证码 </p>
 * @date : 2021/12/23 23:08
 */
public class GifCaptchaRenderer extends AbstractGraphicRenderer {

    @Override
    public Metadata draw() {
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(this.getWidth(), this.getHeight(), this.getLength());
        gifCaptcha.setFont(this.getFont());

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(gifCaptcha.getImageBase64Data());
        metadata.setCharacters(gifCaptcha.getCode());
        return metadata;
    }

    @Override
    public String getCategory() {
        return CaptchaCategory.HUTOOL_GIF.getConstant();
    }
}
