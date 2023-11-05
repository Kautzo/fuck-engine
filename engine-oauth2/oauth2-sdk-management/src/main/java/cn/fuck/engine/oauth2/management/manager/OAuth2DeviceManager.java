package cn.fuck.engine.oauth2.management.manager;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;

public interface OAuth2DeviceManager extends BaseManager<OAuth2Device> {
    boolean activate(String clientId, boolean isActivated);
}
