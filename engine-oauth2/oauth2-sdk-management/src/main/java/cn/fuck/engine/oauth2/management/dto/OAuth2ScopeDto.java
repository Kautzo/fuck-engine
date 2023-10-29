package cn.fuck.engine.oauth2.management.dto;

import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2 Scope Dto </p>
 * @date : 2022/4/1 13:55
 */
@Schema(name = "OAuth2 范围请求 Dto")
public class OAuth2ScopeDto {

    @Schema(name = "范围ID")
    @NotNull(message = "范围ID不能为空")
    private String scopeId;

    @Schema(name = "范围权限列表")
    private Set<OAuth2PermissionDTO> permissions = new HashSet<>();

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public Set<OAuth2PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<OAuth2PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("scopeId", scopeId)
                .toString();
    }
}
