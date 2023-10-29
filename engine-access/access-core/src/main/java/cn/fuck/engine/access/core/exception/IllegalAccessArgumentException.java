package cn.fuck.engine.access.core.exception;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 非法的访问参数错误 </p>
 * @date : 2022/1/26 12:02
 */
public class IllegalAccessArgumentException extends PlatformException {

    public IllegalAccessArgumentException() {
        super();
    }

    public IllegalAccessArgumentException(String message) {
        super(message);
    }

    public IllegalAccessArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAccessArgumentException(Throwable cause) {
        super(cause);
    }

    public IllegalAccessArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ILLEGAL_ACCESS_ARGUMENT;
    }
}
