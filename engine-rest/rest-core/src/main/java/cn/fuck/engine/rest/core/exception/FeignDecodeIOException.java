package cn.fuck.engine.rest.core.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.exception.PlatformException;
import cn.fuck.engine.rest.core.constants.RestErrorCodes;

/**
 * <p>Description: Feign 解码 IO 错误 </p>
 */
public class FeignDecodeIOException extends PlatformException {

    public FeignDecodeIOException() {
        super();
    }

    public FeignDecodeIOException(String message) {
        super(message);
    }

    public FeignDecodeIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignDecodeIOException(Throwable cause) {
        super(cause);
    }

    protected FeignDecodeIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public Feedback getFeedback() {
        return RestErrorCodes.FEIGN_DECODER_IO_EXCEPTION;
    }
}
