package cn.fuck.engine.oauth2.core.definition.domain;

import java.time.LocalDateTime;

/**
 * <p>Description: RegisteredClient 属性定义 </p>
 */
public interface RegisteredClientDetails {

    String getId();

    String getClientId();

    LocalDateTime getClientIdIssuedAt();

    String getClientSecret();

    LocalDateTime getClientSecretExpiresAt();

    String getClientAuthenticationMethods();

    String getAuthorizationGrantTypes();

    String getRedirectUris();

    String getPostLogoutRedirectUris();
}
