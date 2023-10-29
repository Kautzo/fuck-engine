package cn.fuck.engine.access.core.exception;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 接入身份认证错误 </p>
 * @date : 2022/1/26 10:54
 */
public class AccessIdentityVerificationFailedException extends PlatformException {

    public AccessIdentityVerificationFailedException() {
        super();
    }

    public AccessIdentityVerificationFailedException(String message) {
        super(message);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessIdentityVerificationFailedException(Throwable cause) {
        super(cause);
    }

    public AccessIdentityVerificationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return AccessErrorCodes.ACCESS_IDENTITY_VERIFICATION_FAILED;
    }
}
