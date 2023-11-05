package cn.fuck.engine.oauth2.core.exception;


import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;

/**
 * <p> Description : 非法加密Key FuckException </p>
 */
public class IllegalSymmetricKeyException extends PlatformAuthenticationException {

    public IllegalSymmetricKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public IllegalSymmetricKeyException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.ILLEGAL_SYMMETRIC_KEY;
    }
}
