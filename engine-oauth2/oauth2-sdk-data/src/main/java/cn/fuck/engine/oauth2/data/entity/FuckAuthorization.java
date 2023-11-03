package cn.fuck.engine.oauth2.data.entity;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>Description: OAuth2 认证信息 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("oauth2_authorization")
public class FuckAuthorization extends MPEntity {

    @Schema(title = "ID")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id", fill = FieldFill.INSERT)
    protected String id;

    @TableField(value = "registered_client_id")
    private String registeredClientId;

    @TableField(value = "principal_name")
    private String principalName;

    @TableField(value = "authorization_grant_type")
    private String authorizationGrantType;

    @TableField(value = "authorized_scopes")
    private String authorizedScopes;

    @TableField(value = "attributes")
    private String attributes;

    @TableField(value = "state")
    private String state;

    @TableField(value = "authorization_code_value")
    private String authorizationCodeValue;

    @TableField(value = "authorization_code_issued_at")
    private LocalDateTime authorizationCodeIssuedAt;

    @TableField(value = "authorization_code_expires_at")
    private LocalDateTime authorizationCodeExpiresAt;

    @TableField(value = "authorization_code_metadata")
    private String authorizationCodeMetadata;

    @TableField(value = "access_token_value")
    private String accessTokenValue;

    @TableField(value = "access_token_issued_at")
    private LocalDateTime accessTokenIssuedAt;

    @TableField(value = "access_token_expires_at")
    private LocalDateTime accessTokenExpiresAt;

    @TableField(value = "access_token_metadata")
    private String accessTokenMetadata;

    @TableField(value = "access_token_type")
    private String accessTokenType;

    @TableField(value = "access_token_scopes")
    private String accessTokenScopes;

    @TableField(value = "oidc_id_token_value")
    private String oidcIdTokenValue;

    @TableField(value = "oidc_id_token_issued_at")
    private LocalDateTime oidcIdTokenIssuedAt;

    @TableField(value = "oidc_id_token_expires_at")
    private LocalDateTime oidcIdTokenExpiresAt;

    @TableField(value = "oidc_id_token_metadata")
    private String oidcIdTokenMetadata;

    @TableField(value = "oidc_id_token_claims")
    private String oidcIdTokenClaims;

    @TableField(value = "refresh_token_value")
    private String refreshTokenValue;

    @TableField(value = "refresh_token_issued_at")
    private LocalDateTime refreshTokenIssuedAt;

    @TableField(value = "refresh_token_expires_at")
    private LocalDateTime refreshTokenExpiresAt;

    @TableField(value = "refresh_token_metadata")
    private String refreshTokenMetadata;

    @TableField(value = "user_code_value")
    private String userCodeValue;

    @TableField(value = "user_code_issued_at")
    private LocalDateTime userCodeIssuedAt;

    @TableField(value = "user_code_expires_at")
    private LocalDateTime userCodeExpiresAt;

    @TableField(value = "user_code_metadata")
    private String userCodeMetadata;

    @TableField(value = "device_code_value")
    private String deviceCodeValue;

    @TableField(value = "device_code_issued_at")
    private LocalDateTime deviceCodeIssuedAt;

    @TableField(value = "device_code_expires_at")
    private LocalDateTime deviceCodeExpiresAt;

    @TableField(value = "device_code_metadata")
    private String deviceCodeMetadata;

}
