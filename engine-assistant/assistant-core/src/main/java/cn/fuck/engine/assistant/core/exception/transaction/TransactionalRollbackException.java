package cn.fuck.engine.assistant.core.exception.transaction;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;

/**
 * <p>Description: 事务回滚Exception </p>
 * @date : 2021/9/21 11:56
 */
public class TransactionalRollbackException extends PlatformException {

    public TransactionalRollbackException() {
        super();
    }

    public TransactionalRollbackException(String message) {
        super(message);
    }

    public TransactionalRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionalRollbackException(Throwable cause) {
        super(cause);
    }

    protected TransactionalRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return ErrorCodes.TRANSACTION_ROLLBACK;
    }
}
