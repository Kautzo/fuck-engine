package cn.fuck.engine.cache.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.cache.core.constants.CacheErrorCodes;

/**
 * <p>Description: Stamp签章校验错误 </p>
 * @date : 2021/8/23 12:32
 */
public class StampMismatchException extends PlatformException {

    public StampMismatchException() {
        super();
    }

    public StampMismatchException(String message) {
        super(message);
    }

    public StampMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampMismatchException(Throwable cause) {
        super(cause);
    }

    protected StampMismatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_MISMATCH;
    }
}
