package cn.fuck.engine.assistant.core.exception.properties;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: Url 格式错误 </p>
 * @date : 2022/3/6 12:49
 */
public class UrlFormatIncorrectException extends PlatformException {

    public UrlFormatIncorrectException() {
        super();
    }

    public UrlFormatIncorrectException(String message) {
        super(message);
    }

    public UrlFormatIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlFormatIncorrectException(Throwable cause) {
        super(cause);
    }

    protected UrlFormatIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.URL_FORMAT_INCORRECT;
    }
}
