package cn.fuck.engine.oauth2.management.definition;

import cn.fuck.engine.oauth2.core.enums.Signature;
import cn.fuck.engine.oauth2.core.enums.TokenFormat;
import cn.fuck.engine.oauth2.data.definition.domain.AbstractRegisteredClient;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.dromara.hutool.core.data.id.IdUtil;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: 应用对象转 RegisteredClient 共性属性 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractOAuth2RegisteredClient extends AbstractRegisteredClient {

    @Schema(title = "客户端Id", description = "默认为系统自动生成")
    @TableField(value = "client_id")
    private String clientId = IdUtil.fastSimpleUUID();

    @Schema(title = "客户端秘钥", description = "这里存储的客户端秘钥是明文，方便使用。默认为系统自动生成")
    @TableField(value = "client_secret")
    private String clientSecret = IdUtil.fastSimpleUUID();

    /* --- ClientSettings Begin --- */
    @Schema(title = "是否需要证明Key", description = "如果客户端在执行授权码授予流时需要提供验证密钥质询和验证器, 默认False")
    @TableField(value = "require_proof_key")
    private Boolean requireProofKey = Boolean.FALSE;

    @Schema(title = "是否需要认证确认", description = "如果客户端在执行授权码授予流时需要提供验证密钥质询和验证器, 默认False")
    @TableField(value = "require_authorization_consent")
    private Boolean requireAuthorizationConsent = Boolean.TRUE;

    @Schema(title = "客户端JSON Web密钥集的URL", description = "客户端JSON Web密钥集的URL")
    @TableField(value = "jwk_set_url")
    private String jwkSetUrl;

    @Schema(title = "JWT 签名算法", description = "仅在 clientAuthenticationMethods 为 private_key_jwt 和 client_secret_jwt 方法下使用")
    @TableField(value = "signing_algorithm")
    private Signature authenticationSigningAlgorithm;
    /* --- ClientSettings End --- */


    /* --- TokenSettings Begin --- */
    @Schema(title = "授权码有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @TableField(value = "authorization_code_validity")
    private Duration authorizationCodeValidity = Duration.ofMinutes(5);

    @Schema(title = "激活码有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @TableField(value = "device_code_validity")
    private Duration deviceCodeValidity = Duration.ofMinutes(5);

    @Schema(title = "AccessToken 有效时间", description = "默认5分钟，使用 Duration 时间格式")
    @TableField(value = "access_token_validity")
    private Duration accessTokenValidity = Duration.ofMinutes(5);

    @Schema(title = "RefreshToken 有效时间", description = "默认60分钟，使用 Duration 时间格式")
    @TableField(value = "refresh_token_validity")
    private Duration refreshTokenValidity = Duration.ofMinutes(60);

    @Schema(title = "Access Token 格式", description = "OAuth 2.0令牌的标准数据格式")
    @TableField(value = "access_token_format")
    private TokenFormat accessTokenFormat = TokenFormat.REFERENCE;

    @Schema(title = "是否重用 Refresh Token", description = "默认值 True")
    @TableField(value = "reuse_refresh_tokens")
    private Boolean reuseRefreshTokens = Boolean.TRUE;

    @Schema(title = "IdToken 签名算法", description = "JWT 算法用于签名 ID Token， 默认值 RS256")
    @TableField(value = "signature_algorithm")
    private Signature idTokenSignatureAlgorithm = Signature.RS256;
    /* --- TokenSettings End --- */

    public abstract List<OAuth2Scope> getScopes();

}
