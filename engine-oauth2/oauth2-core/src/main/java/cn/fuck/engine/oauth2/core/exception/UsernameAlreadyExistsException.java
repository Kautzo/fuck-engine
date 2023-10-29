package cn.fuck.engine.oauth2.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.oauth2.core.constants.OAuth2ErrorCodes;

/**
 * <p>Description: UsernameAlreadyExistsException </p>
 * @date : 2021/5/17 19:04
 */
public class UsernameAlreadyExistsException extends PlatformAuthenticationException {

    public UsernameAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameAlreadyExistsException(String msg) {
        super(msg);
    }

    @Override
    public Feedback getFeedback() {
        return OAuth2ErrorCodes.USERNAME_ALREADY_EXISTS;
    }
}
