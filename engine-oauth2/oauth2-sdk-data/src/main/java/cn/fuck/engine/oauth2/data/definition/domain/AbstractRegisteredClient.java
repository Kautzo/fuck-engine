package cn.fuck.engine.oauth2.data.definition.domain;

import cn.fuck.engine.assistant.core.json.jackson2.deserializer.CommaDelimitedStringToSetSerializer;
import cn.fuck.engine.assistant.core.json.jackson2.deserializer.SetToCommaDelimitedStringDeserializer;
import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.data.core.entity.BaseSysEntity;
import cn.fuck.engine.oauth2.core.definition.domain.RegisteredClientDetails;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>Description: 多实例共用 RegisteredClient属性 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractRegisteredClient extends BaseSysEntity implements RegisteredClientDetails {

    @Schema(title = "客户端ID发布日期", description = "客户端发布日期")
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT, locale = "GMT+8", shape = JsonFormat.Shape.STRING)
    @TableField(value = "client_id_issued_at", fill = FieldFill.INSERT)
    private LocalDateTime clientIdIssuedAt = LocalDateTime.now();

    @Schema(title = "客户端秘钥过期时间", description = "客户端秘钥过期时间")
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT, locale = "GMT+8", shape = JsonFormat.Shape.STRING)
    @TableField(value = "client_secret_expires_at")
    private LocalDateTime clientSecretExpiresAt;

    @Schema(title = "客户端认证模式", description = "支持多个值，以逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @TableField(value = "client_authentication_methods")
    @JsonDeserialize(using = SetToCommaDelimitedStringDeserializer.class)
    @JsonSerialize(using = CommaDelimitedStringToSetSerializer.class)
    private String clientAuthenticationMethods;

    @Schema(title = "认证模式", description = "支持多个值，以逗号分隔", requiredMode = Schema.RequiredMode.REQUIRED)
    @TableField(value = "authorization_grant_types")
    @JsonDeserialize(using = SetToCommaDelimitedStringDeserializer.class)
    @JsonSerialize(using = CommaDelimitedStringToSetSerializer.class)
    private String authorizationGrantTypes;

    @Schema(title = "回调地址", description = "支持多个值，以逗号分隔")
    @TableField(value = "redirect_uris")
    private String redirectUris;

    @Schema(title = "OIDC Logout 回调地址", description = "支持多个值，以逗号分隔")
    @TableField(value = "post_logout_redirect_uris")
    private String postLogoutRedirectUris;

}
