package cn.fuck.engine.rest.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: 重复提交Exception </p>
 * @date : 2021/8/25 17:43
 */
public class RepeatSubmissionException extends IllegalOperationException {

    public RepeatSubmissionException() {
    }

    public RepeatSubmissionException(String message) {
        super(message);
    }

    public RepeatSubmissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatSubmissionException(Throwable cause) {
        super(cause);
    }

    public RepeatSubmissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.REPEAT_SUBMISSION;
    }
}
