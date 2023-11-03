package cn.fuck.engine.oauth2.authentication.consumer;

import cn.fuck.engine.oauth2.authentication.provider.OAuth2ClientCredentialsAuthenticationProvider;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2ConfigurerUtils;
import cn.fuck.engine.oauth2.core.definition.service.ClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.List;
import java.util.function.Consumer;

/**
 * <p>Description: OAuth2ClientCredentialsAuthenticationProvider 扩展 </p>
 * <p>
 * 用于替换 SAS 默认配置的 OAuth2ClientCredentialsAuthenticationProvider，以实现功能的扩展
 */
@Slf4j
public class OAuth2ClientCredentialsAuthenticationProviderConsumer implements Consumer<List<AuthenticationProvider>> {

    private final HttpSecurity httpSecurity;
    private final ClientDetailsService clientDetailsService;

    public OAuth2ClientCredentialsAuthenticationProviderConsumer(HttpSecurity httpSecurity, ClientDetailsService clientDetailsService) {
        this.httpSecurity = httpSecurity;
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public void accept(List<AuthenticationProvider> authenticationProviders) {
        authenticationProviders.removeIf(authenticationProvider ->
                authenticationProvider instanceof org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationProvider);

        OAuth2AuthorizationService authorizationService = OAuth2ConfigurerUtils.getAuthorizationService(httpSecurity);
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = OAuth2ConfigurerUtils.getTokenGenerator(httpSecurity);
        OAuth2ClientCredentialsAuthenticationProvider provider = new OAuth2ClientCredentialsAuthenticationProvider(authorizationService, tokenGenerator, clientDetailsService);
        log.debug("[FUCK] |- Custom OAuth2ClientCredentialsAuthenticationProvider is in effect!");
        authenticationProviders.add(provider);
    }
}
