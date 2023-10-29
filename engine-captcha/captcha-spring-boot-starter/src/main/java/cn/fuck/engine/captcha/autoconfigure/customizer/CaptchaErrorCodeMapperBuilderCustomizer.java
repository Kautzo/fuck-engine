package cn.fuck.engine.captcha.autoconfigure.customizer;

import cn.fuck.engine.assistant.core.definition.constants.ErrorCodeMapperBuilderOrdered;
import cn.fuck.engine.assistant.core.definition.exception.ErrorCodeMapperBuilderCustomizer;
import cn.fuck.engine.assistant.core.exception.ErrorCodeMapperBuilder;
import cn.fuck.engine.captcha.core.constants.CaptchaErrorCodes;
import org.springframework.core.Ordered;

/**
 * <p>Description: Captcha 错误代码映射定义 </p>
 * @date : 2023/9/25 15:31
 */
public class CaptchaErrorCodeMapperBuilderCustomizer implements ErrorCodeMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(ErrorCodeMapperBuilder builder) {
        builder.notAcceptable(
                CaptchaErrorCodes.CAPTCHA_CATEGORY_IS_INCORRECT,
                CaptchaErrorCodes.CAPTCHA_HANDLER_NOT_EXIST,
                CaptchaErrorCodes.CAPTCHA_HAS_EXPIRED,
                CaptchaErrorCodes.CAPTCHA_IS_EMPTY,
                CaptchaErrorCodes.CAPTCHA_MISMATCH,
                CaptchaErrorCodes.CAPTCHA_PARAMETER_ILLEGAL
        );
    }

    @Override
    public int getOrder() {
        return ErrorCodeMapperBuilderOrdered.CAPTCHA;
    }
}
