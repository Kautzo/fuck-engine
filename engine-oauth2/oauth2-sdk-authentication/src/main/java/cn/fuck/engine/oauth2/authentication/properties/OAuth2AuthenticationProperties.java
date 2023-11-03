package cn.fuck.engine.oauth2.authentication.properties;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import com.google.common.base.MoreObjects;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import java.time.Duration;

/**
 * <p>Description: OAuth2 合规性配置参数 </p>
 */
@Data
@ConfigurationProperties(prefix = OAuth2Constants.PROPERTY_OAUTH2_AUTHENTICATION)
public class OAuth2AuthenticationProperties {

    /**
     * 开启登录失败限制
     */
    private SignInFailureLimited signInFailureLimited = new SignInFailureLimited();

    /**
     * 同一终端登录限制
     */
    private SignInEndpointLimited signInEndpointLimited = new SignInEndpointLimited();

    /**
     * 账户踢出限制
     */
    private SignInKickOutLimited signInKickOutLimited = new SignInKickOutLimited();

    private FormLogin formLogin = new FormLogin();

    @Data
    public static class SignInFailureLimited {
        /**
         * 是否开启登录失败检测，默认开启
         */
        private Boolean enabled = true;

        /**
         * 允许允许最大失败次数
         */
        private Integer maxTimes = 5;

        /**
         * 是否自动解锁被锁定用户，默认开启
         */
        private Boolean autoUnlock = true;

        /**
         * 记录失败次数的缓存过期时间，默认：2小时。
         */
        private Duration expire = Duration.ofHours(2);
    }

    @Data
    public static class SignInEndpointLimited {
        /**
         * 同一终端登录限制是否开启，默认开启。
         */
        private Boolean enabled = false;

        /**
         * 统一终端，允许同时登录的最大数量
         */
        private Integer maximum = 1;

    }

    @Data
    public static class SignInKickOutLimited {
        /**
         * 是否开启 Session 踢出功能，默认开启
         */
        private Boolean enabled = true;

    }

    @Data
    public static class FormLogin {
        /**
         * UI 界面用户名标输入框 name 属性值
         */
        private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
        /**
         * UI 界面密码标输入框 name 属性值
         */
        private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
        /**
         * UI 界面Remember Me name 属性值
         */
        private String rememberMeParameter = AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY;
        /**
         * UI 界面验证码 name 属性值
         */
        private String captchaParameter = "captcha";
        /**
         * 登录页面地址
         */
        private String loginPageUrl = DefaultLoginPageGeneratingFilter.DEFAULT_LOGIN_PAGE_URL;
        /**
         * 登录失败重定向地址
         */
        private String failureForwardUrl = loginPageUrl + SymbolConstants.QUESTION + DefaultLoginPageGeneratingFilter.ERROR_PARAMETER_NAME;
        /**
         * 登录成功重定向地址
         */
        private String successForwardUrl;
        /**
         * 关闭验证码显示，默认 false，显示
         */
        private Boolean closeCaptcha = false;
        /**
         * 验证码类别，默认为 Hutool Gif 类型
         */
        private String category = "HUTOOL_GIF";

    }
}
