package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysUser;
import cn.fuck.engine.upms.manager.SysUserManager;
import cn.fuck.engine.upms.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserManagerImpl extends BaseManagerImpl<SysUserMapper, SysUser> implements SysUserManager {
}
