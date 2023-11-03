package cn.fuck.engine.oauth2.authentication.provider;

import cn.fuck.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2AuthenticationProviderUtils;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2EndpointUtils;
import cn.fuck.engine.oauth2.core.constants.OAuth2ErrorKeys;
import cn.fuck.engine.oauth2.core.definition.service.EnhanceUserDetailsService;
import cn.fuck.engine.oauth2.core.exception.AccountEndpointLimitedException;
import cn.fuck.engine.oauth2.data.storage.FuckOAuth2AuthorizationService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: 抽象的用户认证Provider </p>
 * <p>
 * 提取公共的用户通用基类，方便涉及用户校验的自定义认证模式使用
 * @date : 2022/7/6 16:07
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 */
public abstract class AbstractUserDetailsAuthenticationProvider extends AbstractAuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);

    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final UserDetailsService userDetailsService;
    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2AuthenticationProperties authenticationProperties;
    private PasswordEncoder passwordEncoder;


    public AbstractUserDetailsAuthenticationProvider(OAuth2AuthorizationService authorizationService, UserDetailsService userDetailsService, OAuth2AuthenticationProperties authenticationProperties) {
        this.userDetailsService = userDetailsService;
        this.authorizationService = authorizationService;
        this.authenticationProperties = authenticationProperties;
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    public EnhanceUserDetailsService getUserDetailsService() {
        return (EnhanceUserDetailsService) userDetailsService;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
    }

    protected abstract void additionalAuthenticationChecks(UserDetails userDetails, Map<String, Object> additionalParameters) throws AuthenticationException;

    protected abstract UserDetails retrieveUser(Map<String, Object> additionalParameters) throws AuthenticationException;

    private Authentication authenticateUserDetails(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
        UserDetails user = retrieveUser(additionalParameters);

        if (!user.isAccountNonLocked()) {
            log.debug("[FUCK] |- Failed to authenticate since user account is locked");
            throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
        }
        if (!user.isEnabled()) {
            log.debug("[FUCK] |- Failed to authenticate since user account is disabled");
            throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
        }
        if (!user.isAccountNonExpired()) {
            log.debug("[FUCK] |- Failed to authenticate since user account has expired");
            throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
        }

        additionalAuthenticationChecks(user, additionalParameters);

        if (!user.isCredentialsNonExpired()) {
            log.debug("[FUCK] |- Failed to authenticate since user account credentials have expired");
            throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
        }

        if (authenticationProperties.getSignInEndpointLimited().getEnabled() && !authenticationProperties.getSignInKickOutLimited().getEnabled()) {
            if (authorizationService instanceof FuckOAuth2AuthorizationService fuckOAuth2AuthorizationService) {
                int count = fuckOAuth2AuthorizationService.findAuthorizationCount(registeredClientId, user.getUsername());
                if (count >= authenticationProperties.getSignInEndpointLimited().getMaximum()) {
                    throw new AccountEndpointLimitedException("Use same endpoint signIn exceed limit");
                }
            }
        }

        if (!authenticationProperties.getSignInEndpointLimited().getEnabled() && authenticationProperties.getSignInKickOutLimited().getEnabled()) {
            if (authorizationService instanceof FuckOAuth2AuthorizationService fuckOAuth2AuthorizationService) {
                List<OAuth2Authorization> authorizations = fuckOAuth2AuthorizationService.findAvailableAuthorizations(registeredClientId, user.getUsername());
                if (CollectionUtils.isNotEmpty(authorizations)) {
                    authorizations.forEach(authorization -> {
                        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getToken(OAuth2RefreshToken.class);
                        if (ObjectUtils.isNotEmpty(refreshToken)) {
                            authorization = OAuth2AuthenticationProviderUtils.invalidate(authorization, refreshToken.getToken());
                        }
                        log.debug("[FUCK] |- Sign in user [{}] with token id [{}] will be kicked out.", user.getUsername(), authorization.getId());
                        fuckOAuth2AuthorizationService.save(authorization);
                    });
                }
            }
        }

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    protected Authentication getUsernamePasswordAuthentication(Map<String, Object> additionalParameters, String registeredClientId) throws AuthenticationException {
        Authentication authentication = null;
        try {
            authentication = authenticateUserDetails(additionalParameters, registeredClientId);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            String exceptionName = ase.getClass().getSimpleName();
            OAuth2EndpointUtils.throwError(
                    exceptionName,
                    ase.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        } catch (BadCredentialsException bce) {
            OAuth2EndpointUtils.throwError(
                    OAuth2ErrorKeys.BAD_CREDENTIALS,
                    bce.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        } catch (UsernameNotFoundException unfe) {
            OAuth2EndpointUtils.throwError(
                    OAuth2ErrorKeys.USERNAME_NOT_FOUND,
                    unfe.getMessage(),
                    OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        return authentication;
    }
}
