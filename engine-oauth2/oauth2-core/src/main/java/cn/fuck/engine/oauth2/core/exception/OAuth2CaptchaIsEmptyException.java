package cn.fuck.engine.oauth2.core.exception;

import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;

/**
 * <p>Description: 验证码为空 </p>
 */
public class OAuth2CaptchaIsEmptyException extends OAuth2CaptchaException {

    public OAuth2CaptchaIsEmptyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaIsEmptyException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_IS_EMPTY;
    }
}
