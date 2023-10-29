package cn.fuck.engine.oauth2.core.exception;

import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;

/**
 * <p>Description: Oauth2 使用的验证码参数错误 </p>
 * @date : 2021/12/24 12:02
 */
public class OAuth2CaptchaArgumentIllegalException extends OAuth2CaptchaException {

    public OAuth2CaptchaArgumentIllegalException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public OAuth2CaptchaArgumentIllegalException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_PARAMETER_ILLEGAL;
    }
}
