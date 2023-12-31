package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.MPEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_permission")
public class SysRolePermission extends MPEntity {

    @Schema(title = "id")
    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @Schema(title = "角色Id")
    @TableField(value = "role_id")
    private String roleId;

    @Schema(title = "权限Id")
    @TableField(value = "permission_id")
    private String permissionId;
}
