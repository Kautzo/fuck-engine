package cn.fuck.engine.captcha.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;

/**
 * <p>Description: 验证码分类错误 </p>
 * @date : 2021/12/15 17:51
 */
public class CaptchaCategoryIsIncorrectException extends PlatformException {

    public CaptchaCategoryIsIncorrectException() {
        super();
    }

    public CaptchaCategoryIsIncorrectException(String message) {
        super(message);
    }

    public CaptchaCategoryIsIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaCategoryIsIncorrectException(Throwable cause) {
        super(cause);
    }

    protected CaptchaCategoryIsIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CaptchaErrorCodes.CAPTCHA_CATEGORY_IS_INCORRECT;
    }
}
