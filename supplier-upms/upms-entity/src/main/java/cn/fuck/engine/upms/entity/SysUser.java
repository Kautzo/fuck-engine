package cn.fuck.engine.upms.entity;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>Description: 系统用户 </p>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(title = "系统用户")
@TableName("sys_user")
public class SysUser extends BaseSysEntity {

    @Schema(title = "用户名")
    @TableField(value = "user_name")
    private String userName;

    @Schema(title = "密码", description = "BCryptPasswordEncoder")
    @TableField(value = "password")
    private String password;

    @Schema(title = "真名")
    @TableField(value = "real_name")
    private String realName;

    @Schema(title = "手机号码")
    @TableField(value = "phone_number")
    private String phoneNumber;

    @Schema(title = "头像")
    @TableField(value = "avatar")
    private String avatar;

    @Schema(title = "EMAIL")
    @TableField(value = "email")
    private String email;

    @Schema(title = "账户过期日期")
    @TableField(value = "account_expire_at")
    private LocalDateTime accountExpireAt;

    @Schema(title = "密码过期日期")
    @TableField(value = "credentials_expire_at")
    private LocalDateTime credentialsExpireAt;

    @Schema(title = "默认租户Id")
    @TableField(value = "tenant_id", fill = FieldFill.INSERT_UPDATE)
    protected String tenantId;
}
