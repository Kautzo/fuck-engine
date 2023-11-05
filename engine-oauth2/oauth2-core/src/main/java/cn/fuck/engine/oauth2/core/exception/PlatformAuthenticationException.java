package cn.fuck.engine.oauth2.core.exception;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.definition.exception.FuckException;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.domain.Result;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>Description: 平台认证基础Exception </p>
 */
public class PlatformAuthenticationException extends AuthenticationException implements FuckException {

    public PlatformAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PlatformAuthenticationException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.INTERNAL_SERVER_ERROR;
    }

    @Override
    public Result<String> getResult() {
        Result<String> result = Result.failure(getFeedback());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}
