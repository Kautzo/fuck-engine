package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface OAuth2ApplicationScopeService
        extends BaseService<OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope> {

    List<OAuth2ApplicationScope> getByApplicationId(String applicationId);

    void removeByApplicationIds(Collection<String> applicationIds);
    void removeByScopeIds(Collection<String> scopeIds);

}
