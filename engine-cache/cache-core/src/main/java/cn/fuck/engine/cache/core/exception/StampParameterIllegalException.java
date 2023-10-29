package cn.fuck.engine.cache.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.cache.core.constants.CacheErrorCodes;

/**
 * <p>Description: 请求参数中缺少幂等Token错误 </p>
 * @date : 2021/8/23 12:29
 */
public class StampParameterIllegalException extends PlatformException {

    public StampParameterIllegalException() {
        super();
    }

    public StampParameterIllegalException(String message) {
        super(message);
    }

    public StampParameterIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public StampParameterIllegalException(Throwable cause) {
        super(cause);
    }

    protected StampParameterIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return CacheErrorCodes.STAMP_PARAMETER_ILLEGAL;
    }
}
