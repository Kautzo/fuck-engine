package cn.fuck.engine.captcha.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码处理器不存在 </p>
 */
public class CaptchaHandlerNotExistException extends PlatformException {

    public CaptchaHandlerNotExistException() {
        super();
    }

    public CaptchaHandlerNotExistException(String message) {
        super(message);
    }

    public CaptchaHandlerNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaHandlerNotExistException(Throwable cause) {
        super(cause);
    }

    protected CaptchaHandlerNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_HANDLER_NOT_EXIST;
    }
}
