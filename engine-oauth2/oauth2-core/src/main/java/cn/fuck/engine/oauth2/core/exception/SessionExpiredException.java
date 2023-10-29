package cn.fuck.engine.oauth2.core.exception;

import org.springframework.security.authentication.AccountStatusException;

/**
 * <p>Description: 自定义 Session 已过期 </p>
 * @date : 2022/7/28 13:37
 */
public class SessionExpiredException extends AccountStatusException {

    public SessionExpiredException(String msg) {
        super(msg);
    }

    public SessionExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
