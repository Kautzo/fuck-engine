package cn.fuck.engine.upms.service;

import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysUserRole;

import java.util.Collection;
import java.util.List;

public interface SysUserRoleService
        extends BaseService<SysUserRole, SysUserRole, SysUserRole, SysUserRole, SysUserRole> {

    List<SysUserRole> getByUserId(String userId);

    void removeByUserIds(Collection<String> userIds);
    void removeByRoleIds(Collection<String> roleIds);

}
