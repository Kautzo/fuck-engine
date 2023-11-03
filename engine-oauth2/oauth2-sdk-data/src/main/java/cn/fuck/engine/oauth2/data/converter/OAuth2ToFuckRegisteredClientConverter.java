package cn.fuck.engine.oauth2.data.converter;

import cn.fuck.engine.oauth2.data.definition.converter.AbstractOAuth2EntityConverter;
import cn.fuck.engine.oauth2.data.jackson2.OAuth2JacksonProcessor;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import org.dromara.hutool.core.date.DateUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: RegisteredClient 转 FuckRegisteredClient 转换器 </p>
 */
public class OAuth2ToFuckRegisteredClientConverter extends AbstractOAuth2EntityConverter<RegisteredClient, FuckRegisteredClient> {

    private final PasswordEncoder passwordEncoder;

    public OAuth2ToFuckRegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor, PasswordEncoder passwordEncoder) {
        super(jacksonProcessor);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public FuckRegisteredClient convert(RegisteredClient registeredClient) {
        List<String> clientAuthenticationMethods = new ArrayList<>(registeredClient.getClientAuthenticationMethods().size());
        registeredClient.getClientAuthenticationMethods().forEach(clientAuthenticationMethod ->
                clientAuthenticationMethods.add(clientAuthenticationMethod.getValue()));

        List<String> authorizationGrantTypes = new ArrayList<>(registeredClient.getAuthorizationGrantTypes().size());
        registeredClient.getAuthorizationGrantTypes().forEach(authorizationGrantType ->
                authorizationGrantTypes.add(authorizationGrantType.getValue()));

        FuckRegisteredClient entity = new FuckRegisteredClient();
        entity.setId(registeredClient.getId());
        entity.setClientId(registeredClient.getClientId());
        entity.setClientIdIssuedAt(DateUtil.toLocalDateTime(registeredClient.getClientIdIssuedAt()));
        entity.setClientSecret(encode(registeredClient.getClientSecret()));
        entity.setClientSecretExpiresAt(DateUtil.toLocalDateTime(registeredClient.getClientSecretExpiresAt()));
        entity.setClientName(registeredClient.getClientName());
        entity.setClientAuthenticationMethods(StringUtils.collectionToCommaDelimitedString(clientAuthenticationMethods));
        entity.setAuthorizationGrantTypes(StringUtils.collectionToCommaDelimitedString(authorizationGrantTypes));
        entity.setRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getRedirectUris()));
        entity.setPostLogoutRedirectUris(StringUtils.collectionToCommaDelimitedString(registeredClient.getPostLogoutRedirectUris()));
        entity.setScopes(StringUtils.collectionToCommaDelimitedString(registeredClient.getScopes()));
        entity.setClientSettings(writeMap(registeredClient.getClientSettings().getSettings()));
        entity.setTokenSettings(writeMap(registeredClient.getTokenSettings().getSettings()));

        return entity;
    }

    private String encode(String value) {
        if (value != null) {
            return this.passwordEncoder.encode(value);
        }
        return null;
    }
}
