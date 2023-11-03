package cn.fuck.engine.upms.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.message.core.domain.RequestMapping;
import cn.fuck.engine.upms.entity.SysInterface;

import java.util.Collection;
import java.util.List;

public interface SysInterfaceService extends BaseService<SysInterface> {

    List<SysInterface> getAllocatable();

    List<SysInterface> storeRequestMappings(Collection<RequestMapping> requestMappings);
}
