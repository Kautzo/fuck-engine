package cn.fuck.engine.oauth2.core.exception;

import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;

/**
 * <p>Description: Oauth2 使用的验证码不匹配错误 </p>
 * @date : 2021/12/24 12:08
 */
public class OAuth2CaptchaMismatchException extends OAuth2CaptchaException {

    public OAuth2CaptchaMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaMismatchException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_MISMATCH;
    }
}
