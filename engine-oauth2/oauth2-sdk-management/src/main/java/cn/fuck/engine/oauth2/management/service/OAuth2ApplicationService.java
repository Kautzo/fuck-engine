package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.dto.OAuth2ApplicationDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Set;

public interface OAuth2ApplicationService
        extends BaseService<OAuth2Application, OAuth2ApplicationDTO, OAuth2ApplicationDTO, OAuth2Application, OAuth2Application> {

    void updateScope(String applicationId, Set<String> scopeIds);

    OAuth2Application getByClientId(String clientId);

}
