package cn.fuck.engine.upms.service;

import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysRolePermission;

import java.util.Collection;
import java.util.List;

public interface SysRolePermissionService
        extends BaseService<SysRolePermission, SysRolePermission, SysRolePermission, SysRolePermission, SysRolePermission> {

    List<SysRolePermission> getByRoleId(String roleId);

    void removeByRoleIdS(Collection<String> roleId);
    void removeByPermissionIdS(Collection<String> permissionId);

}
