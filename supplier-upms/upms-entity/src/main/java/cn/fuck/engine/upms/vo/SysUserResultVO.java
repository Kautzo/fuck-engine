package cn.fuck.engine.upms.vo;

import cn.fuck.engine.data.core.entity.BaseSysEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUserResultVO extends BaseSysEntity {

    @Schema(title = "用户名")
    private String userName;

    @Schema(title = "真名")
    private String realName;

    @Schema(title = "手机号码")
    private String phoneNumber;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "EMAIL")
    private String email;

    @Schema(title = "账户过期日期")
    private LocalDateTime accountExpireAt;

    @Schema(title = "密码过期日期")
    private LocalDateTime credentialsExpireAt;

    @Schema(title = "默认租户Id")
    private String tenantId;
}
