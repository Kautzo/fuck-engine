package cn.fuck.engine.oauth2.authentication.configurer;

import cn.fuck.engine.captcha.core.processor.CaptchaRendererFactory;
import cn.fuck.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.fuck.engine.oauth2.authentication.provider.OAuth2FormLoginAuthenticationProvider;
import cn.fuck.engine.oauth2.authentication.response.OAuth2FormLoginAuthenticationFailureHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * <p>Description: OAuth2 Form Login Configurer </p>
 * <p>
 * 使用此种方式，相当于额外增加了一种表单登录方式。因此对原有的 http.formlogin进行的配置，对当前此种方式的配置并不生效。
 * @see org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer
 */
public class OAuth2FormLoginSecureConfigurer<H extends HttpSecurityBuilder<H>> extends AbstractHttpConfigurer<OAuth2FormLoginSecureConfigurer<H>, H> {

    private final UserDetailsService userDetailsService;
    private final OAuth2AuthenticationProperties authenticationProperties;
    private final CaptchaRendererFactory captchaRendererFactory;

    public OAuth2FormLoginSecureConfigurer(UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties, CaptchaRendererFactory captchaRendererFactory) {
        this.userDetailsService = userDetailsService;
        this.authenticationProperties = authenticationProperties;
        this.captchaRendererFactory = captchaRendererFactory;
    }

    @Override
    public void configure(H httpSecurity) throws Exception {

        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        SecurityContextRepository securityContextRepository = httpSecurity.getSharedObject(SecurityContextRepository.class);

        OAuth2FormLoginAuthenticationFilter filter = getOAuth2FormLoginAuthenticationFilter(authenticationManager, securityContextRepository);

        OAuth2FormLoginAuthenticationProvider provider = new OAuth2FormLoginAuthenticationProvider(captchaRendererFactory);
        provider.setUserDetailsService(userDetailsService);
        provider.setHideUserNotFoundExceptions(false);

        httpSecurity
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @NotNull
    private OAuth2FormLoginAuthenticationFilter getOAuth2FormLoginAuthenticationFilter(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        OAuth2FormLoginAuthenticationFilter filter = new OAuth2FormLoginAuthenticationFilter(authenticationManager);
        filter.setUsernameParameter(getFormLogin().getUsernameParameter());
        filter.setPasswordParameter(getFormLogin().getPasswordParameter());
        filter.setAuthenticationDetailsSource(new OAuth2FormLoginWebAuthenticationDetailSource(authenticationProperties));

        filter.setAuthenticationFailureHandler(new OAuth2FormLoginAuthenticationFailureHandler(getFormLogin().getFailureForwardUrl()));
        filter.setSecurityContextRepository(securityContextRepository);
        return filter;
    }

    private OAuth2AuthenticationProperties.FormLogin getFormLogin() {
        return authenticationProperties.getFormLogin();
    }
}
