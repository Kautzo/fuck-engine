package cn.fuck.engine.rest.core.exception;

import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 非法操作Exception </p>
 * @date : 2021/8/25 17:43
 */
public class IllegalOperationException extends PlatformException {

    public IllegalOperationException() {
    }

    public IllegalOperationException(String message) {
        super(message);
    }

    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalOperationException(Throwable cause) {
        super(cause);
    }

    public IllegalOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
