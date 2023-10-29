package cn.fuck.engine.rest.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import cn.fuck.engine.rest.core.constants.RestErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Rest 错误代码映射定义 </p>
 * @date : 2023/9/26 23:20
 */
public class RestErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                RestErrorCodes.SESSION_INVALID,
                RestErrorCodes.REPEAT_SUBMISSION,
                RestErrorCodes.FREQUENT_REQUESTS,
                RestErrorCodes.FEIGN_DECODER_IO_EXCEPTION
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.REST;
    }
}
