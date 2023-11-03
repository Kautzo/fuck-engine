package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>Description: 系统权限实体 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统权限")
@TableName("sys_permission")
public class SysPermission extends BaseSysEntity {

    @Schema(description = "权限代码")
    @TableField(value = "permission_code")
    private String permissionCode;

    @Schema(description = "权限名称")
    @TableField(value = "permission_name")
    private String permissionName;
}
