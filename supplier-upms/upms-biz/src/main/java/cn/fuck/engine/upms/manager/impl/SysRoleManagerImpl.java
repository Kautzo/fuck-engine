package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysRole;
import cn.fuck.engine.upms.mapper.SysRoleMapper;
import cn.fuck.engine.upms.manager.SysRoleManager;
import org.springframework.stereotype.Service;

@Service
public class SysRoleManagerImpl extends BaseManagerImpl<SysRoleMapper, SysRole> implements SysRoleManager {
}
