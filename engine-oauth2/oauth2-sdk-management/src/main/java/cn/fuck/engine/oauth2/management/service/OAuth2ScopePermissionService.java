package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface OAuth2ScopePermissionService
        extends BaseService<OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission> {

    List<OAuth2ScopePermission> getByScopeId(String scopeId);
    List<OAuth2ScopePermission> getByScopeIds(List<String> scopeId);

    void removeByScopeIds(Collection<String> scopeIds);
    void removeByPermissionIds(Collection<String> permissionIds);
}
