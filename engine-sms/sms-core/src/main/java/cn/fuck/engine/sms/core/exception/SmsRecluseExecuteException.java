package cn.fuck.engine.sms.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.sms.core.constants.SmsErrorCodes;

/**
 * <p>Description: Recluse SMS 执行错误 </p>
 * @date : 2022/7/25 20:34
 */
public class SmsRecluseExecuteException extends PlatformException {

    public SmsRecluseExecuteException() {
        super();
    }

    public SmsRecluseExecuteException(String message) {
        super(message);
    }

    public SmsRecluseExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsRecluseExecuteException(Throwable cause) {
        super(cause);
    }

    protected SmsRecluseExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return SmsErrorCodes.SMS_RECLUSE_EXECUTE_ERROR;
    }
}
