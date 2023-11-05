package cn.fuck.engine.oauth2.core.exception;

/**
 * <p>Description: 社交登录绑定用户出错 </p>
 */
public class SocialCredentialsUserBindingFailedException extends PlatformAuthenticationException {

    public SocialCredentialsUserBindingFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SocialCredentialsUserBindingFailedException(String msg) {
        super(msg);
    }
}
