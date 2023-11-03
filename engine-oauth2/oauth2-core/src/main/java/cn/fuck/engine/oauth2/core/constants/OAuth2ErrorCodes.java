package cn.fuck.engine.oauth2.core.constants;

import cn.fuck.engine.assistant.core.exception.feedback.NotAcceptableFeedback;

/**
 * <p>Description: OAuth2 错误代码 </p>
 */
public interface OAuth2ErrorCodes {

    NotAcceptableFeedback USERNAME_ALREADY_EXISTS = new NotAcceptableFeedback("用户名已经存在");
}
