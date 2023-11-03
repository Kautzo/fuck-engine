package cn.fuck.engine.rest.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: 操作频繁Exception </p>
 */
public class FrequentRequestsException extends IllegalOperationException {

    public FrequentRequestsException() {
    }

    public FrequentRequestsException(String message) {
        super(message);
    }

    public FrequentRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrequentRequestsException(Throwable cause) {
        super(cause);
    }

    public FrequentRequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.FREQUENT_REQUESTS;
    }
}
