package cn.fuck.engine.assistant.core.definition.exception;

import cn.fuck.engine.assistant.core.domain.Feedback;
import cn.fuck.engine.assistant.core.domain.Result;

/**
 * <p>Description: 核心 FuckException 定义 </p>
 */
public interface FuckException {

    /**
     * 获取反馈信息
     *
     * @return 反馈信息对象 {@link Feedback}
     */
    Feedback getFeedback();

    /**
     * 错误信息转换为 Result 对象。
     *
     * @return 结果对象 {@link Result}
     */
    Result<String> getResult();
}
