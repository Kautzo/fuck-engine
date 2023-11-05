package cn.fuck.engine.upms.entity;

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
@TableName("sys_attribute_permission")
public class SysAttributePermission extends MPEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @TableField(value = "attribute_id")
    private String attributeId;

    @TableField(value = "permission_id")
    private String permissionId;

}
