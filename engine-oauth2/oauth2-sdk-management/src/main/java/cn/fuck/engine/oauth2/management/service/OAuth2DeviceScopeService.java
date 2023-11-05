package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2DeviceScope;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface OAuth2DeviceScopeService
        extends BaseService<OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope> {
    List<OAuth2DeviceScope> getByDeviceId(String deviceId);

    void removeByDeviceIds(Collection<String> deviceIds);
    void removeByScopeIds(Collection<String> scopeIds);
}
