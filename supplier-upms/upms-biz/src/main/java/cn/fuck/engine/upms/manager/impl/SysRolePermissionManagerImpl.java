package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.manager.SysRolePermissionManager;
import cn.fuck.engine.upms.mapper.SysRolePermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermissionManagerImpl extends BaseManagerImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionManager {
}
