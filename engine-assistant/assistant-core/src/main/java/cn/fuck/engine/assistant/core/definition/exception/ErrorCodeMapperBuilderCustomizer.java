package cn.fuck.engine.assistant.core.definition.exception;

import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;

/**
 * <p>Description: ErrorCodeMapperBuilder 回调接口</p>
 * <p>
 * 实现了该接口的Bean，可以在自动配置阶段，通过ErrorCodeMapperBuilder进一步扩展错误码
 */
@FunctionalInterface
public interface ErrorCodeMapperBuilderCustomizer {

    /**
     * 自定义 ErrorCodeMapperBuilder
     *
     * @param builder 被扩展的 {@link ErrorCodeMapperBuilder}
     */
    void customize(ErrorCodeMapperBuilder builder);
}
