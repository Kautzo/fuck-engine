package cn.fuck.engine.supplier.upms.logic.converter;

import cn.fuck.engine.message.core.domain.RequestMapping;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysInterface;
import org.springframework.core.convert.converter.Converter;

/**
 * <p>Description: RequestMapping 转 SysInterface 转换器 </p>
 * @date : 2023/5/23 17:15
 */
public class RequestMappingToSysInterfaceConverter implements Converter<RequestMapping, SysInterface> {

    @Override
    public SysInterface convert(RequestMapping requestMapping) {
        SysInterface sysInterface = new SysInterface();
        sysInterface.setInterfaceId(requestMapping.getMappingId());
        sysInterface.setInterfaceCode(requestMapping.getMappingCode());
        sysInterface.setRequestMethod(requestMapping.getRequestMethod());
        sysInterface.setServiceId(requestMapping.getServiceId());
        sysInterface.setClassName(requestMapping.getClassName());
        sysInterface.setMethodName(requestMapping.getMethodName());
        sysInterface.setUrl(requestMapping.getUrl());
        sysInterface.setDescription(requestMapping.getDescription());
        return sysInterface;
    }
}
