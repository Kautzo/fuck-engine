package cn.fuck.engine.oauth2.authentication.provider;

import cn.fuck.engine.captcha.core.dto.Verification;
import cn.fuck.engine.captcha.core.exception.CaptchaHasExpiredException;
import cn.fuck.engine.captcha.core.exception.CaptchaIsEmptyException;
import cn.fuck.engine.captcha.core.exception.CaptchaMismatchException;
import cn.fuck.engine.captcha.core.exception.CaptchaParameterIllegalException;
import cn.fuck.engine.captcha.core.processor.CaptchaRendererFactory;
import cn.fuck.engine.oauth2.core.definition.details.FormLoginWebAuthenticationDetails;
import cn.fuck.engine.oauth2.core.exception.OAuth2CaptchaArgumentIllegalException;
import cn.fuck.engine.oauth2.core.exception.OAuth2CaptchaHasExpiredException;
import cn.fuck.engine.oauth2.core.exception.OAuth2CaptchaIsEmptyException;
import cn.fuck.engine.oauth2.core.exception.OAuth2CaptchaMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>Description: OAuth2 (Security) 表单登录 Provider </p>
 * <p>
 * 扩展的OAuth2表单登录Provider，以支持表单登录的验证码
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */
@Slf4j
public class OAuth2FormLoginAuthenticationProvider extends DaoAuthenticationProvider {

    private final CaptchaRendererFactory captchaRendererFactory;

    public OAuth2FormLoginAuthenticationProvider(CaptchaRendererFactory captchaRendererFactory) {
        super();
        this.captchaRendererFactory = captchaRendererFactory;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object details = authentication.getDetails();

        if (ObjectUtils.isNotEmpty(details) && details instanceof FormLoginWebAuthenticationDetails formLoginWebAuthenticationDetails) {

            if (!formLoginWebAuthenticationDetails.getClosed()) {

                String code = formLoginWebAuthenticationDetails.getCode();
                String category = formLoginWebAuthenticationDetails.getCategory();
                String identity = formLoginWebAuthenticationDetails.getIdentity();

                if (StringUtils.isBlank(code)) {
                    throw new OAuth2CaptchaIsEmptyException("Captcha is empty.");
                }

                try {
                    Verification verification = new Verification();
                    verification.setCharacters(code);
                    verification.setCategory(category);
                    verification.setIdentity(identity);
                    captchaRendererFactory.verify(verification);
                } catch (CaptchaParameterIllegalException e) {
                    throw new OAuth2CaptchaArgumentIllegalException("Captcha argument is illegal!");
                } catch (CaptchaHasExpiredException e) {
                    throw new OAuth2CaptchaHasExpiredException("Captcha is expired!");
                } catch (CaptchaMismatchException e) {
                    throw new OAuth2CaptchaMismatchException("Captcha is mismatch!");
                } catch (CaptchaIsEmptyException e) {
                    throw new OAuth2CaptchaIsEmptyException("Captcha is empty!");
                }
            }
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        boolean supports = (OAuth2FormLoginAuthenticationToken.class.isAssignableFrom(authentication));
        log.trace("[FUCK] |- Form Login Authentication is supports! [{}]", supports);
        return supports;
    }
}
