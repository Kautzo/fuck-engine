package cn.fuck.engine.upms.manager;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.upms.entity.SysInterface;

import java.util.List;

public interface SysInterfaceManager extends BaseManager<SysInterface> {

    List<SysInterface> getAllocatable();

}
