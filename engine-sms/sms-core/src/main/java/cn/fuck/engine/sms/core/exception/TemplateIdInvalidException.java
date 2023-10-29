package cn.fuck.engine.sms.core.exception;

/**
 * <p>Description: 短信模版ID不可用错误 </p>
 * @date : 2021/5/25 16:16
 */
public class TemplateIdInvalidException extends SmsSendException {

    public TemplateIdInvalidException() {
    }

    public TemplateIdInvalidException(String message) {
        super(message);
    }

    public TemplateIdInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateIdInvalidException(Throwable cause) {
        super(cause);
    }

    public TemplateIdInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
