package cn.fuck.engine.captcha.core.constants;

import cn.fuck.engine.assistant.core.exception.feedback.NotAcceptableFeedback;

/**
 * <p>Description: Captcha 错误代码 </p>
 */
public interface CaptchaErrorCodes {

    NotAcceptableFeedback CAPTCHA_CATEGORY_IS_INCORRECT = new NotAcceptableFeedback("验证码分类错误");
    NotAcceptableFeedback CAPTCHA_HANDLER_NOT_EXIST = new NotAcceptableFeedback("验证码处理器不存在");
    NotAcceptableFeedback CAPTCHA_HAS_EXPIRED = new NotAcceptableFeedback("验证码已过期");
    NotAcceptableFeedback CAPTCHA_IS_EMPTY = new NotAcceptableFeedback("验证码不能为空");
    NotAcceptableFeedback CAPTCHA_MISMATCH = new NotAcceptableFeedback("验证码不匹配");
    NotAcceptableFeedback CAPTCHA_PARAMETER_ILLEGAL = new NotAcceptableFeedback("验证码参数格式错误");

}
