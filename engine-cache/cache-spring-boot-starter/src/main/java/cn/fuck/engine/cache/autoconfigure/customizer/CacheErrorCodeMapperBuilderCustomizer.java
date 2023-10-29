package cn.fuck.engine.cache.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import cn.fuck.engine.cache.core.constants.CacheErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Cache 模块内置错误代码生成器 </p>
 */
public class CacheErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                CacheErrorCodes.STAMP_DELETE_FAILED,
                CacheErrorCodes.STAMP_HAS_EXPIRED,
                CacheErrorCodes.STAMP_MISMATCH,
                CacheErrorCodes.STAMP_PARAMETER_ILLEGAL
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.CACHE;
    }
}
