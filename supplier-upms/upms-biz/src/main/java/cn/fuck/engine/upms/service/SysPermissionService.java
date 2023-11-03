package cn.fuck.engine.upms.service;


import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends BaseService<SysPermission> {
    Boolean handlerDelete(List<String> ids);
}
