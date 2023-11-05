package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.message.core.domain.RequestMapping;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.converter.RequestMappingToSysInterfaceConverter;
import cn.fuck.engine.upms.entity.SysInterface;
import cn.fuck.engine.upms.manager.SysInterfaceManager;
import cn.fuck.engine.upms.service.SysInterfaceService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: 系统应用程序接口 Service </p>
 */
@Service
public class SysInterfaceServiceImpl
        extends BaseServiceImpl<SysInterfaceManager, SysInterface, SysInterface, SysInterface, SysInterface, SysInterface>
        implements SysInterfaceService {

    private final Converter<RequestMapping, SysInterface> toSysInterface = new RequestMappingToSysInterfaceConverter();

    /**
     * 查找SysSecurityAttribute中不存在的SysAuthority
     *
     * @return SysAuthority列表
     */
    @Override
    public List<SysInterface> getAllocatable() {
        return baseManger.getAllocatable();
    }

    @Override
    @Transactional
    public List<SysInterface> storeRequestMappings(Collection<RequestMapping> requestMappings) {
        List<SysInterface> sysAuthorities = toSysInterfaces(requestMappings);
        saveBatch(sysAuthorities);
        return sysAuthorities;
    }

    private List<SysInterface> toSysInterfaces(Collection<RequestMapping> requestMappings) {
        if (CollectionUtils.isNotEmpty(requestMappings)) {
            return requestMappings.stream().map(toSysInterface::convert).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
