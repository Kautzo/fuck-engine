package cn.fuck.engine.assistant.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.constants.ErrorCodes;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import org.springframework.core.Ordered;

/**
 * <p>Description: 标准内置错误代码 </p>
 */
public class StandardErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder
                .unauthorized(ErrorCodes.ACCESS_DENIED,
                        ErrorCodes.ACCOUNT_DISABLED,
                        ErrorCodes.ACCOUNT_ENDPOINT_LIMITED,
                        ErrorCodes.ACCOUNT_EXPIRED,
                        ErrorCodes.ACCOUNT_LOCKED,
                        ErrorCodes.BAD_CREDENTIALS,
                        ErrorCodes.CREDENTIALS_EXPIRED,
                        ErrorCodes.INVALID_CLIENT,
                        ErrorCodes.INVALID_TOKEN,
                        ErrorCodes.INVALID_GRANT,
                        ErrorCodes.UNAUTHORIZED_CLIENT,
                        ErrorCodes.USERNAME_NOT_FOUND,
                        ErrorCodes.SESSION_EXPIRED)
                .forbidden(ErrorCodes.INSUFFICIENT_SCOPE, ErrorCodes.SQL_INJECTION_REQUEST)
                .methodNotAllowed(ErrorCodes.HTTP_REQUEST_METHOD_NOT_SUPPORTED)
                .notAcceptable(ErrorCodes.UNSUPPORTED_GRANT_TYPE, ErrorCodes.UNSUPPORTED_RESPONSE_TYPE, ErrorCodes.UNSUPPORTED_TOKEN_TYPE)
                .preconditionFailed(ErrorCodes.INVALID_REDIRECT_URI, ErrorCodes.INVALID_REQUEST, ErrorCodes.INVALID_SCOPE)
                .unsupportedMediaType(ErrorCodes.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE)
                .internalServerError(ErrorCodes.SERVER_ERROR,
                        ErrorCodes.HTTP_MESSAGE_NOT_READABLE_EXCEPTION,
                        ErrorCodes.ILLEGAL_ARGUMENT_EXCEPTION,
                        ErrorCodes.IO_EXCEPTION,
                        ErrorCodes.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION,
                        ErrorCodes.NULL_POINTER_EXCEPTION,
                        ErrorCodes.TYPE_MISMATCH_EXCEPTION)
                .notImplemented(ErrorCodes.PROPERTY_VALUE_IS_NOT_SET, ErrorCodes.URL_FORMAT_INCORRECT, ErrorCodes.ILLEGAL_SYMMETRIC_KEY)
                .serviceUnavailable(ErrorCodes.COOKIE_THEFT, ErrorCodes.INVALID_COOKIE, ErrorCodes.PROVIDER_NOT_FOUND, ErrorCodes.TEMPORARILY_UNAVAILABLE, ErrorCodes.SEARCH_IP_LOCATION)
                .customize(ErrorCodes.TRANSACTION_ROLLBACK);
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.STANDARD;
    }
}
