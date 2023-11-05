package cn.fuck.engine.captcha.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码已过期 </p>
 */
public class CaptchaHasExpiredException extends PlatformException {

    public CaptchaHasExpiredException() {
        super();
    }

    public CaptchaHasExpiredException(String message) {
        super(message);
    }

    public CaptchaHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaHasExpiredException(Throwable cause) {
        super(cause);
    }

    protected CaptchaHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_HAS_EXPIRED;
    }
}
