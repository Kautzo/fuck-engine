package cn.fuck.engine.oauth2.authorization.customizer;

import cn.fuck.engine.oauth2.authorization.processor.SecurityAuthorizationManager;
import cn.fuck.engine.oauth2.authorization.processor.SecurityMatcherConfigurer;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * <p>Description: AuthorizeHttpRequestsConfigurer 扩展配置 </p>
 */
public class OAuth2AuthorizeHttpRequestsConfigurerCustomer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {

    private final SecurityMatcherConfigurer securityMatcherConfigurer;
    private final SecurityAuthorizationManager securityAuthorizationManager;

    public OAuth2AuthorizeHttpRequestsConfigurerCustomer(SecurityMatcherConfigurer securityMatcherConfigurer, SecurityAuthorizationManager securityAuthorizationManager) {
        this.securityMatcherConfigurer = securityMatcherConfigurer;
        this.securityAuthorizationManager = securityAuthorizationManager;
    }

    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configurer) {
        configurer
                .requestMatchers(securityMatcherConfigurer.getStaticResourceArray()).permitAll()
                .requestMatchers(securityMatcherConfigurer.getPermitAllArray()).permitAll()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .anyRequest().access(securityAuthorizationManager);
    }
}
