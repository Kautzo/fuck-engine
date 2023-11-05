package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;
import cn.fuck.engine.oauth2.management.manager.OAuth2DeviceManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2DeviceMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

@Service
public class OAuth2DeviceManagerImpl
        extends BaseManagerImpl<OAuth2DeviceMapper, OAuth2Device>
        implements OAuth2DeviceManager {
    @Override
    public boolean activate(String clientId, boolean isActivated) {
        return update(Wrappers.lambdaUpdate(OAuth2Device.class)
                .set(OAuth2Device::getActivated, isActivated)
                .eq(OAuth2Device::getClientId, clientId));
    }
}
