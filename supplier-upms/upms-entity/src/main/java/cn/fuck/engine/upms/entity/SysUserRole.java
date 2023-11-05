package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRole extends MPEntity {

    @Schema(title = "id")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @Schema(title = "租户Id")
    @TableField(value = "user_id")
    private String userId;

    @Schema(title = "角色Id")
    @TableField(value = "role_id")
    private String roleId;

    @Schema(title = "租户Id")
    @TableField(value = "tenant_id")
    protected String tenantId;

}
