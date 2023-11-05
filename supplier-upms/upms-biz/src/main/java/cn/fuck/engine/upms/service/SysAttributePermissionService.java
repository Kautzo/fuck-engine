package cn.fuck.engine.upms.service;

import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysAttributePermission;

import java.util.Collection;
import java.util.List;

public interface SysAttributePermissionService
        extends BaseService<SysAttributePermission, SysAttributePermission, SysAttributePermission, SysAttributePermission, SysAttributePermission> {
    List<SysAttributePermission> getByAttributeId(String attributeId);

    void removeByAttributeIds(Collection<String> attributeIds);
    void removeByPermissionIds(Collection<String> permissionIds);

}
