package cn.fuck.engine.oauth2.management.entity;

import cn.fuck.engine.oauth2.management.definition.AbstractOAuth2RegisteredClient;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>Description: 物联网设备管理 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "物联网设备")
@TableName("oauth2_device")
public class OAuth2Device extends AbstractOAuth2RegisteredClient {

    @Schema(description = "设备名称")
    @TableField(value = "device_name")
    private String deviceName;

    @Schema(description = "产品ID")
    @TableField(value = "product_id")
    private String productId;

    @Schema(title = "是否已激活", description = "设备是否已经激活状态标记，默认值false，即未激活")
    @TableField(value = "is_activated")
    private Boolean activated = Boolean.FALSE;

    @TableField(exist = false)
    @Schema(title = "设备对应Scope", description = "传递设备对应Scope ID数组")
    private List<OAuth2Scope> scopes;

}
