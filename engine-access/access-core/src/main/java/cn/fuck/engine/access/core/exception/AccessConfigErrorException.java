package cn.fuck.engine.access.core.exception;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: Access 配置错误 </p>
 * @date : 2022/9/2 18:02
 */
public class AccessConfigErrorException extends PlatformException {

    public AccessConfigErrorException() {
        super();
    }

    public AccessConfigErrorException(String message) {
        super(message);
    }

    public AccessConfigErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessConfigErrorException(Throwable cause) {
        super(cause);
    }

    protected AccessConfigErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_CONFIG_ERROR;
    }
}
