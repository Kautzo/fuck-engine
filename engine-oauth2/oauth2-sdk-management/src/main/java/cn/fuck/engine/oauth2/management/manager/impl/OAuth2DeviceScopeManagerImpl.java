package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2DeviceScope;
import cn.fuck.engine.oauth2.management.manager.OAuth2DeviceScopeManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2DeviceScopeMapper;
import org.springframework.stereotype.Service;

@Service
public class OAuth2DeviceScopeManagerImpl extends BaseManagerImpl<OAuth2DeviceScopeMapper, OAuth2DeviceScope> implements OAuth2DeviceScopeManager {
}
