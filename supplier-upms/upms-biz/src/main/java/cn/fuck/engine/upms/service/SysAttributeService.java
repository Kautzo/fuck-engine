package cn.fuck.engine.upms.service;

import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysAttribute;

import java.util.List;

public interface SysAttributeService
        extends BaseService<SysAttribute, SysAttribute, SysAttribute, SysAttribute, SysAttribute> {

    void updateAttributePermission(String attributeId, List<String> permissionIds);

    List<SysAttribute> getByServiceId(String serviceId);

}
