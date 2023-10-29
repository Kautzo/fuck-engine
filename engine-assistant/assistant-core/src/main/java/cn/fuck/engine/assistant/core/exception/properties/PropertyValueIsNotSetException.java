package cn.fuck.engine.assistant.core.exception.properties;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: Property 属性值没有设置错误 </p>
 * @date : 2022/3/6 13:57
 */
public class PropertyValueIsNotSetException extends PlatformException {

    public PropertyValueIsNotSetException() {
        super();
    }

    public PropertyValueIsNotSetException(String message) {
        super(message);
    }

    public PropertyValueIsNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyValueIsNotSetException(Throwable cause) {
        super(cause);
    }

    protected PropertyValueIsNotSetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.PROPERTY_VALUE_IS_NOT_SET;
    }
}
