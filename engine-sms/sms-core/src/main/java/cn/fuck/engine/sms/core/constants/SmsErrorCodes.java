package cn.fuck.engine.sms.core.constants;

import cn.fuck.engine.assistant.core.exception.feedback.InternalServerErrorFeedback;

/**
 * <p>Description: Recluse 错误代码 </p>
 * @date : 2022/7/25 20:31
 */
public interface SmsErrorCodes {

    InternalServerErrorFeedback SMS_RECLUSE_EXECUTE_ERROR = new InternalServerErrorFeedback("短信平台操作错误");
}
