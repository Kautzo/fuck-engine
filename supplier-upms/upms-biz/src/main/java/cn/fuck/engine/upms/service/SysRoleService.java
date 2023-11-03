package cn.fuck.engine.upms.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysRole;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {

    void updateRolePermission(String roleId, List<String> permissionIds);

    SysRole getByRoleCode(String roleCode);

    Boolean handlerDelete(List<String> ids);
}
