package cn.fuck.engine.oauth2.data.entity;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Description: OAuth2 认证确认信息实体 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("oauth2_authorization_consent")
public class FuckAuthorizationConsent extends MPEntity {

    @TableField(value = "registered_client_id")
    private String registeredClientId;

    @TableField(value = "principal_name")
    private String principalName;

    @TableField(value = "authorities")
    private String authorities;

}
