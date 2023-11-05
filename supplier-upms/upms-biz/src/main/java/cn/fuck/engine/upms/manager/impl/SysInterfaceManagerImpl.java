package cn.fuck.engine.upms.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.upms.entity.SysInterface;
import cn.fuck.engine.upms.manager.SysInterfaceManager;
import cn.fuck.engine.upms.mapper.SysInterfaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysInterfaceManagerImpl extends BaseManagerImpl<SysInterfaceMapper, SysInterface> implements SysInterfaceManager {

    @Override
    public List<SysInterface> getAllocatable() {
        return baseMapper.getAllocatable();
    }
}
