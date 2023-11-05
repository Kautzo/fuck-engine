package cn.fuck.engine.oauth2.management.entity;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("oauth2_scope_permission")
public class OAuth2ScopePermission extends MPEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @TableField(value = "scope_id")
    private String scopeId;

    @TableField(value = "permission_id")
    private String permissionId;

    @TableField(value = "permission_code")
    private String permissionCode;

}
