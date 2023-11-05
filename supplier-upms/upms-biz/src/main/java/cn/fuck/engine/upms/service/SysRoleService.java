package cn.fuck.engine.upms.service;

import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysRole;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole, SysRole, SysRole, SysRole, SysRole> {

    void updateRolePermission(String roleId, List<String> permissionIds);

    SysRole getByRoleCode(String roleCode);

}
