package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.management.dto.OAuth2ApplicationDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;

import java.util.List;
import java.util.Set;

public interface OAuth2ApplicationService extends BaseService<OAuth2Application> {
    void updateScope(String applicationId, Set<String> scopeIds);

    OAuth2Application findByClientId(String clientId);

    void handlerSave(OAuth2ApplicationDTO oAuth2ApplicationDTO);

    void handlerUpdate(OAuth2ApplicationDTO oAuth2ApplicationDTO);

    Boolean handlerDelete(List<String> ids);
}
