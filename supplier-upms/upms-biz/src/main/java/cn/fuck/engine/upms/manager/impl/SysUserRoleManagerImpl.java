package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.manager.SysUserRoleManager;
import cn.fuck.engine.upms.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleManagerImpl extends BaseManagerImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleManager {
}
