package cn.fuck.engine.assistant.ip2region.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: TODO </p>
 */
public class SearchIpLocationException extends PlatformException {

    public SearchIpLocationException() {
        super();
    }

    public SearchIpLocationException(String message) {
        super(message);
    }

    public SearchIpLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchIpLocationException(Throwable cause) {
        super(cause);
    }

    protected SearchIpLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return super.getFeedback();
    }
}
