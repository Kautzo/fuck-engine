package cn.fuck.engine.oauth2.authentication.provider;

import cn.fuck.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2AuthenticationProviderUtils;
import cn.fuck.engine.oauth2.core.definition.FuckGrantType;
import cn.fuck.engine.oauth2.core.definition.service.EnhanceUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * <p>Description: 自定义 OAuth2 密码模式认证 Provider </p>
 */
@Slf4j
public class OAuth2ResourceOwnerPasswordAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private SessionRegistry sessionRegistry;

    /**
     * Constructs an {@code OAuth2ClientCredentialsAuthenticationProvider} using the provided parameters.
     *
     * @param authorizationService the authorization service
     * @param tokenGenerator       – the token generator
     */
    public OAuth2ResourceOwnerPasswordAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, UserDetailsService userDetailsService, OAuth2AuthenticationProperties complianceProperties) {
        super(authorizationService, userDetailsService, complianceProperties);
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException {
        String presentedPassword = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);
        if (!this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            log.debug("[FUCK] |- Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException {
        String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);

        try {
            EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
            UserDetails userDetails = enhanceUserDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return userDetails;
        } catch (UsernameNotFoundException ex) {
            log.error("[FUCK] |- User name can not found ：[{}]", username);
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2ResourceOwnerPasswordAuthenticationToken resourceOwnerPasswordAuthentication =
                (OAuth2ResourceOwnerPasswordAuthenticationToken) authentication;

        OAuth2ClientAuthenticationToken clientPrincipal =
                OAuth2AuthenticationProviderUtils.getAuthenticatedClientElseThrowInvalidClient(resourceOwnerPasswordAuthentication);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        if (!registeredClient.getAuthorizationGrantTypes().contains(FuckGrantType.PASSWORD)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        Authentication principal = getUsernamePasswordAuthentication(resourceOwnerPasswordAuthentication.getAdditionalParameters(), registeredClient.getId());

        // Default to configured scopes
        Set<String> authorizedScopes = validateScopes(resourceOwnerPasswordAuthentication.getScopes(), registeredClient);

        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(principal.getName())
                .authorizationGrantType(FuckGrantType.PASSWORD)
                .authorizedScopes(authorizedScopes)
                .attribute(Principal.class.getName(), principal);

        // @formatter:off
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(principal)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(FuckGrantType.PASSWORD)
                .authorizationGrant(resourceOwnerPasswordAuthentication);
        // @formatter:on

        // ----- Access token -----
        OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI);

        // ----- Refresh token -----
        OAuth2RefreshToken refreshToken = creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, clientPrincipal, registeredClient);

        // ----- ID token -----
        OidcIdToken idToken = createOidcIdToken(principal, sessionRegistry, tokenContextBuilder, authorizationBuilder, this.tokenGenerator, ERROR_URI, resourceOwnerPasswordAuthentication.getScopes());

        OAuth2Authorization authorization = authorizationBuilder.build();

        this.authorizationService.save(authorization);

        log.debug("[FUCK] |- Resource Owner Password returning OAuth2AccessTokenAuthenticationToken.");

        Map<String, Object> additionalParameters = idTokenAdditionalParameters(idToken);

        OAuth2AccessTokenAuthenticationToken accessTokenAuthenticationToken = new OAuth2AccessTokenAuthenticationToken(
                registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
        return createOAuth2AccessTokenAuthenticationToken(principal, accessTokenAuthenticationToken);
    }

    /**
     * Sets the {@link SessionRegistry} used to track OpenID Connect sessions.
     *
     * @param sessionRegistry the {@link SessionRegistry} used to track OpenID Connect sessions
     * @since 1.1.1
     */
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        Assert.notNull(sessionRegistry, "sessionRegistry cannot be null");
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean supports = OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
        log.trace("[FUCK] |- Resource Owner Password Authentication is supports! [{}]", supports);
        return supports;
    }


}
