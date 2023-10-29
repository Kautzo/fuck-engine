package cn.fuck.engine.message.core.constants;

import cn.fuck.engine.assistant.core.exception.feedback.NotAcceptableFeedback;

/**
 * <p>Description: WebSocket 统一错误代码定义 </p>
 * @date : 2022/12/29 15:57
 */
public interface MessageErrorCodes {

    NotAcceptableFeedback ILLEGAL_CHANNEL = new NotAcceptableFeedback("WebSocket Channel 设置错误");
    NotAcceptableFeedback PRINCIPAL_NOT_FOUND = new NotAcceptableFeedback("WebSocket 无法获取用户身份信息");
}
