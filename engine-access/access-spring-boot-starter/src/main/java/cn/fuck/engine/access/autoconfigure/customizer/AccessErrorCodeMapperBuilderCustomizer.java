package cn.fuck.engine.access.autoconfigure.customizer;

import cn.fuck.engine.access.core.constants.AccessErrorCodes;
import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import org.springframework.core.Ordered;

/**
 * <p>Description: Access 错误代码映射定义 </p>
 * @date : 2023/9/26 23:35
 */
public class AccessErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.preconditionFailed(
                AccessErrorCodes.ACCESS_CONFIG_ERROR,
                AccessErrorCodes.ACCESS_HANDLER_NOT_FOUND,
                AccessErrorCodes.ACCESS_IDENTITY_VERIFICATION_FAILED,
                AccessErrorCodes.ACCESS_PRE_PROCESS_FAILED_EXCEPTION,
                AccessErrorCodes.ILLEGAL_ACCESS_ARGUMENT,
                AccessErrorCodes.ILLEGAL_ACCESS_SOURCE
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.ACCESS;
    }
}
