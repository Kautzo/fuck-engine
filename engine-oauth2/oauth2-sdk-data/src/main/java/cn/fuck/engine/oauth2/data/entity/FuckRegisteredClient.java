package cn.fuck.engine.oauth2.data.entity;

import cn.fuck.engine.oauth2.data.definition.domain.AbstractRegisteredClient;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>Description: OAuth2 客户端实体 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("oauth2_registered_client")
public class FuckRegisteredClient extends AbstractRegisteredClient {

    @TableField(value = "client_id")
    private String clientId;

    @TableField(value = "client_secret")
    private String clientSecret;

    @TableField(value = "client_name")
    private String clientName;

    @TableField(value = "scopes")
    private String scopes;

    @TableField(value = "client_settings")
    private String clientSettings;

    @TableField(value = "token_settings")
    private String tokenSettings;

}
