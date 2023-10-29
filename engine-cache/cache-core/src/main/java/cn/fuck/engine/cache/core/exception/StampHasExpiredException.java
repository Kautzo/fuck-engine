package cn.fuck.engine.cache.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.cache.core.constants.CacheErrorCodes;

/**
 * <p>Description: Stamp签章 已过期错误 </p>
 * @date : 2021/8/23 12:36
 */
public class StampHasExpiredException extends PlatformException {

    public StampHasExpiredException() {
        super();
    }

    public StampHasExpiredException(String message) {
        super(message);
    }

    public StampHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampHasExpiredException(Throwable cause) {
        super(cause);
    }

    protected StampHasExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_HAS_EXPIRED;
    }
}
