package cn.fuck.engine.captcha.hutool.renderer;

import cn.fuck.engine.captcha.core.definition.AbstractGraphicRenderer;
import cn.fuck.engine.captcha.core.definition.domain.Metadata;
import cn.fuck.engine.captcha.core.definition.enums.CaptchaCategory;
import org.dromara.hutool.swing.captcha.CaptchaUtil;
import org.dromara.hutool.swing.captcha.LineCaptcha;
import org.springframework.stereotype.Component;

/**
 * <p>Description: Hutool Line Captcha </p>
 */
@Component
public class LineCaptchaRenderer extends AbstractGraphicRenderer {

    @Override
    public Metadata draw() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(this.getWidth(), this.getHeight(), this.getLength(), 150);
        lineCaptcha.setFont(this.getFont());

        Metadata metadata = new Metadata();
        metadata.setGraphicImageBase64(lineCaptcha.getImageBase64Data());
        metadata.setCharacters(lineCaptcha.getCode());
        return metadata;
    }

    @Override
    public String getCategory() {
        return CaptchaCategory.HUTOOL_LINE.getConstant();
    }
}
