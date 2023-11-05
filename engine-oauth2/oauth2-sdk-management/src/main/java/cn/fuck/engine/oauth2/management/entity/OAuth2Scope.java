package cn.fuck.engine.oauth2.management.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import cn.fuck.engine.upms.entity.SysPermission;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p> Description : Oauth Scope </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("oauth2_scope")
public class OAuth2Scope extends BaseSysEntity {

    @TableField(value = "scope_code")
    private String scopeCode;

    @TableField(value = "scope_name")
    private String scopeName;

    @TableField(exist = false)
    private List<SysPermission> permissions;

}
