package cn.fuck.engine.oauth2.management.definition;

import cn.fuck.engine.oauth2.data.definition.converter.RegisteredClientConverter;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: OAuth2Application  </p>
 */
public abstract class AbstractOAuth2RegisteredClientConverter<T extends AbstractOAuth2RegisteredClient> implements RegisteredClientConverter<T> {

    @Override
    public Set<String> getScopes(T details) {
        List<OAuth2Scope> clientScopes = details.getScopes();
        return clientScopes.stream().map(OAuth2Scope::getScopeCode).collect(Collectors.toSet());
    }

    @Override
    public ClientSettings getClientSettings(T details) {
        ClientSettings.Builder clientSettingsBuilder = ClientSettings.builder();
        clientSettingsBuilder.requireAuthorizationConsent(details.getRequireAuthorizationConsent());
        clientSettingsBuilder.requireProofKey(details.getRequireProofKey());
        if (StringUtils.hasText(details.getJwkSetUrl())) {
            clientSettingsBuilder.jwkSetUrl(details.getJwkSetUrl());
        }
        if (ObjectUtils.isNotEmpty(details.getAuthenticationSigningAlgorithm())) {
            JwsAlgorithm jwsAlgorithm = SignatureAlgorithm.from(details.getAuthenticationSigningAlgorithm().name());
            if (ObjectUtils.isNotEmpty(jwsAlgorithm)) {
                clientSettingsBuilder.tokenEndpointAuthenticationSigningAlgorithm(jwsAlgorithm);
            }
        }
        return clientSettingsBuilder.build();
    }

    @Override
    public TokenSettings getTokenSettings(T details) {
        TokenSettings.Builder tokenSettingsBuilder = TokenSettings.builder();
        tokenSettingsBuilder.authorizationCodeTimeToLive(details.getAuthorizationCodeValidity());
        tokenSettingsBuilder.deviceCodeTimeToLive(details.getDeviceCodeValidity());
        tokenSettingsBuilder.accessTokenTimeToLive(details.getAccessTokenValidity());
        // refreshToken 的有效期
        tokenSettingsBuilder.refreshTokenTimeToLive(details.getRefreshTokenValidity());
        // 是否可重用刷新令牌
        tokenSettingsBuilder.reuseRefreshTokens(details.getReuseRefreshTokens());
        tokenSettingsBuilder.accessTokenFormat(new OAuth2TokenFormat(details.getAccessTokenFormat().getFormat()));
        if (ObjectUtils.isNotEmpty(details.getIdTokenSignatureAlgorithm())) {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.from(details.getIdTokenSignatureAlgorithm().name());
            if (ObjectUtils.isNotEmpty(signatureAlgorithm)) {
                tokenSettingsBuilder.idTokenSignatureAlgorithm(signatureAlgorithm);
            }
        }
        return tokenSettingsBuilder.build();
    }
}
