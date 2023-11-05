package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.management.entity.OAuth2DeviceScope;
import cn.fuck.engine.oauth2.management.manager.OAuth2DeviceScopeManager;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceScopeService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class OAuth2DeviceScopeServiceImpl
        extends BaseServiceImpl<OAuth2DeviceScopeManager, OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope, OAuth2DeviceScope>
        implements OAuth2DeviceScopeService {

    @Override
    public List<OAuth2DeviceScope> getByDeviceId(String deviceId) {
        return baseManger.list(Wrappers.lambdaQuery(OAuth2DeviceScope.class).eq(OAuth2DeviceScope::getDeviceId, deviceId));
    }

    @Override
    public void removeByDeviceIds(Collection<String> deviceIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2DeviceScope.class)
                .in(OAuth2DeviceScope::getDeviceId, deviceIds));
    }

    @Override
    public void removeByScopeIds(Collection<String> scopeIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2DeviceScope.class)
                .in(OAuth2DeviceScope::getScopeId, scopeIds));
    }
}
