package cn.fuck.engine.oauth2.core.definition.domain;

import java.time.LocalDateTime;

/**
 * <p>Description: RegisteredClient 属性定义 </p>
 * @date : 2023/5/12 23:10
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
