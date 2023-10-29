package cn.fuck.engine.assistant.core.definition.exception;

import cn.fuck.engine.assistant.core.domain.Result;

/**
 * <p>Description: 自定义错误基础类 </p>
 * @date : 2022/3/4 18:31
 */
public abstract class AbstractHerodotusException extends RuntimeException implements HerodotusException {

    public AbstractHerodotusException() {
        super();
    }

    public AbstractHerodotusException(String message) {
        super(message);
    }

    public AbstractHerodotusException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractHerodotusException(Throwable cause) {
        super(cause);
    }

    protected AbstractHerodotusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
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
