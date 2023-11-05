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
@TableName(value = "oauth2_application_scope")
public class OAuth2ApplicationScope extends MPEntity {

    @TableId(type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private String id;

    @TableField(value = "application_id")
    private String applicationId;

    @TableField(value = "scope_id")
    private String scopeId;

}
