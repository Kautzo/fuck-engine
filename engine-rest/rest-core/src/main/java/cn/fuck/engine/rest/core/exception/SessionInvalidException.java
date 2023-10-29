package cn.fuck.engine.rest.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: Session 不可用错误 </p>
 * @date : 2021/10/4 16:46
 */
public class SessionInvalidException extends PlatformException {

    public SessionInvalidException() {
        super();
    }

    public SessionInvalidException(String message) {
        super(message);
    }

    public SessionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionInvalidException(Throwable cause) {
        super(cause);
    }

    public SessionInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.SESSION_INVALID;
    }
}
