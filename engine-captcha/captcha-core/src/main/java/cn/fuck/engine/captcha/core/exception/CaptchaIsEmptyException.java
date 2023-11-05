package cn.fuck.engine.captcha.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码为空 </p>
 */
public class CaptchaIsEmptyException extends PlatformException {

    public CaptchaIsEmptyException() {
        super();
    }

    public CaptchaIsEmptyException(String message) {
        super(message);
    }

    public CaptchaIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaIsEmptyException(Throwable cause) {
        super(cause);
    }

    protected CaptchaIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_IS_EMPTY;
    }
}
