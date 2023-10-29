package cn.fuck.engine.assistant.core.exception;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.definition.exception.AbstractHerodotusException;
import cn.fuck.engine.assistant.core.domain.Feedback;

/**
 * <p>Description: 平台基础Exception </p>
 * @date : 2019/12/18 15:31
 */
public class PlatformException extends AbstractHerodotusException {

    public PlatformException() {
        super();
    }

    public PlatformException(String message) {
        super(message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }

    protected PlatformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.INTERNAL_SERVER_ERROR;
    }
}
