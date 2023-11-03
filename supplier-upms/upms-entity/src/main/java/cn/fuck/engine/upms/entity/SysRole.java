package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色")
@TableName("sys_role")
public class SysRole extends BaseSysEntity {

    @Schema(description = "角色编码")
    @TableField(value = "role_code")
    private String roleCode;

    @Schema(description = "角色名称")
    @TableField(value = "role_name")
    private String roleName;
}
