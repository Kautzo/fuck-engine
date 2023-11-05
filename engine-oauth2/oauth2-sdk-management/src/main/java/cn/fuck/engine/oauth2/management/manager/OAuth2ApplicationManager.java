package cn.fuck.engine.oauth2.management.manager;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;

public interface OAuth2ApplicationManager extends BaseManager<OAuth2Application> {
    OAuth2Application getByClientId(String clientId);
}
