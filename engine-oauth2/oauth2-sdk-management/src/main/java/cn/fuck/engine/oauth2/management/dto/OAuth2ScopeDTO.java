package cn.fuck.engine.oauth2.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2 Scope DTO </p>
 */
@Data
@Schema(name = "OAuth2 范围请求 DTO")
public class OAuth2ScopeDTO {

    @Schema(name = "范围ID")
    @NotBlank(message = "范围ID不能为空")
    private String scopeId;

    @Schema(name = "范围权限列表")
    private Set<OAuth2PermissionDTO> permissions = new HashSet<>();

}
