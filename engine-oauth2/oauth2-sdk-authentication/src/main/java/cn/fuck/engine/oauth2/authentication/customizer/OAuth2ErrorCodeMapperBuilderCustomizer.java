package cn.fuck.engine.oauth2.authentication.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import cn.fuck.engine.oauth2.core.constants.OAuth2ErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: OAuth2 Authentication 模块内置错误代码生成器 </p>
 * @date : 2023/9/26 22:11
 */
public class OAuth2ErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                OAuth2ErrorCodes.USERNAME_ALREADY_EXISTS
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.OAUTH2;
    }
}
