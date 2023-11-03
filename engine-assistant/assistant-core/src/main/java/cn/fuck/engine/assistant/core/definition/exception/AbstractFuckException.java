package cn.fuck.engine.assistant.core.definition.exception;

import cn.fuck.engine.assistant.core.domain.Result;

/**
 * <p>Description: 自定义错误基础类 </p>
 */
public abstract class AbstractFuckException extends RuntimeException implements FuckException {

    public AbstractFuckException() {
        super();
    }

    public AbstractFuckException(String message) {
        super(message);
    }

    public AbstractFuckException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractFuckException(Throwable cause) {
        super(cause);
    }

    protected AbstractFuckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Result<String> getResult() {
        Result<String> result = Result.failure(getFeedback());
        result.stackTrace(super.getStackTrace());
        result.detail(super.getMessage());
        return result;
    }
}
