package cn.fuck.engine.upms.dto;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Schema(title = "系统用户")
public class SysUserSaveDTO {

    @NotBlank
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

    @Schema(title = "数据状态")
    protected DataItemStatus status = DataItemStatus.ENABLE;

    @Schema(title = "是否为保留数据", description = "True 为不能删，False为可以删除")
    protected Boolean reserved = Boolean.FALSE;

    @Schema(description = "备注")
    protected String description;

}
