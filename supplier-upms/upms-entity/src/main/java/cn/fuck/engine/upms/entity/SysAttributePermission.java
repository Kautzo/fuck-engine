package cn.fuck.engine.upms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("sys_attribute_permission")
public class SysAttributePermission {

    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @TableField(value = "attribute_id")
    private String attributeId;

    @TableField(value = "permission_id")
    private String permissionId;

}
