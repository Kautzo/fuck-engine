package cn.fuck.engine.message.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import cn.fuck.engine.message.core.constants.MessageErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Message 错误代码映射定义 </p>
 * @date : 2023/9/26 23:27
 */
public class MessageErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(MessageErrorCodes.ILLEGAL_CHANNEL, MessageErrorCodes.PRINCIPAL_NOT_FOUND);
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.MESSAGE;
    }
}
