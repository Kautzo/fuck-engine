package cn.fuck.engine.oauth2.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <p>Description: OAuth2 FuckPermission DTO </p>
 */
@Data
@Schema(name = "OAuth2 权限请求 DTO")
public class OAuth2PermissionDTO {

    @Schema(name = "权限ID")
    @NotBlank(message = "权限ID不能为空")
    private String permissionId;

    @Schema(name = "权限代码")
    @NotBlank(message = "权限代码不能为空")
    private String permissionCode;

}
