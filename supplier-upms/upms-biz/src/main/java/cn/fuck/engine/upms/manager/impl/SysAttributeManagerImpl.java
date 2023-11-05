package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysAttribute;
import cn.fuck.engine.upms.manager.SysAttributeManager;
import cn.fuck.engine.upms.mapper.SysAttributeMapper;
import org.springframework.stereotype.Service;

@Service
public class SysAttributeManagerImpl extends BaseManagerImpl<SysAttributeMapper, SysAttribute> implements SysAttributeManager {
}
