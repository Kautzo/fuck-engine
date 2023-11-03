package cn.fuck.engine.upms.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysAttribute;

import java.util.List;

public interface SysAttributeService extends BaseService<SysAttribute> {

    void updateAttributePermission(String attributeId, List<String> permissionIds);

    List<SysAttribute> getByServiceId(String serviceId);

    Boolean handlerDelete(List<String> ids);
}
