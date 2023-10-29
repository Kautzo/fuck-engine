package cn.fuck.engine.oauth2.management.dto;

import cn.fuck.engine.rest.core.definition.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Description: OAuth2 HerodotusPermission DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "OAuth2 权限请求 DTO")
public class OAuth2PermissionDTO extends BaseDTO {

    @Schema(name = "权限ID")
    @NotNull(message = "权限ID不能为空")
    private String permissionId;

    @Schema(name = "权限代码")
    @NotNull(message = "权限代码不能为空")
    private String permissionCode;

    @Schema(name = "服务ID")
    @NotNull(message = "服务ID不能为空")
    private String permissionName;

}
