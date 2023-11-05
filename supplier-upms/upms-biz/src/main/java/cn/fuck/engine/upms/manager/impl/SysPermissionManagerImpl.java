package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.manager.SysPermissionManager;
import cn.fuck.engine.upms.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class SysPermissionManagerImpl extends BaseManagerImpl<SysPermissionMapper, SysPermission> implements SysPermissionManager {
}
