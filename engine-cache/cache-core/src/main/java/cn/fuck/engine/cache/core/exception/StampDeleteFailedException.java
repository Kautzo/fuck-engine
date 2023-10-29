package cn.fuck.engine.cache.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.cache.core.constants.CacheErrorCodes;

/**
 * <p>Description: Stamp签章删除失败Exception </p>
 * @date : 2021/8/23 13:51
 */
public class StampDeleteFailedException extends PlatformException {

    public StampDeleteFailedException() {
        super();
    }

    public StampDeleteFailedException(String message) {
        super(message);
    }

    public StampDeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampDeleteFailedException(Throwable cause) {
        super(cause);
    }

    protected StampDeleteFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_DELETE_FAILED;
    }
}
