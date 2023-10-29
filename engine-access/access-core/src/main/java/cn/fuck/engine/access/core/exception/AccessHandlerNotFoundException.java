package cn.fuck.engine.access.core.exception;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 接入处理器未找到错误 </p>
 * @date : 2022/1/26 12:03
 */
public class AccessHandlerNotFoundException extends PlatformException {

    public AccessHandlerNotFoundException() {
        super();
    }

    public AccessHandlerNotFoundException(String message) {
        super(message);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessHandlerNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccessHandlerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_HANDLER_NOT_FOUND;
    }
}
