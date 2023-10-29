package cn.fuck.engine.message.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.message.core.constants.MessageErrorCodes;

/**
 * <p>Description: Web Socket Channel 错误 </p>
 * @date : 2021/10/24 18:45
 */
public class IllegalChannelException extends PlatformException {

    public IllegalChannelException() {
        super();
    }

    public IllegalChannelException(String message) {
        super(message);
    }

    public IllegalChannelException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalChannelException(Throwable cause) {
        super(cause);
    }

    protected IllegalChannelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return MessageErrorCodes.ILLEGAL_CHANNEL;
    }
}
