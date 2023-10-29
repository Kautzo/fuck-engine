package cn.fuck.engine.access.core.exception;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 接入预操作失败错误 </p>
 * @date : 2022/1/26 11:10
 */
public class AccessPreProcessFailedException extends PlatformException {

    public AccessPreProcessFailedException() {
    }

    public AccessPreProcessFailedException(String message) {
        super(message);
    }

    public AccessPreProcessFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessPreProcessFailedException(Throwable cause) {
        super(cause);
    }

    public AccessPreProcessFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_PRE_PROCESS_FAILED_EXCEPTION;
    }
}
