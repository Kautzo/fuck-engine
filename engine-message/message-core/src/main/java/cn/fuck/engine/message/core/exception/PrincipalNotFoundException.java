package cn.fuck.engine.message.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.message.core.constants.MessageErrorCodes;

/**
 * <p>Description: 无法找到 Principal 错误 </p>
 * @date : 2021/10/24 18:45
 */
public class PrincipalNotFoundException extends PlatformException {

    public PrincipalNotFoundException() {
        super();
    }

    public PrincipalNotFoundException(String message) {
        super(message);
    }

    public PrincipalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrincipalNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PrincipalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return MessageErrorCodes.PRINCIPAL_NOT_FOUND;
    }
}
