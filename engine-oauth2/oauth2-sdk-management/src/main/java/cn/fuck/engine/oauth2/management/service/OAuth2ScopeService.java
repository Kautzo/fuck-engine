package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.dto.OAuth2ScopeDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.List;

public interface OAuth2ScopeService
        extends BaseService<OAuth2Scope, OAuth2Scope, OAuth2Scope, OAuth2Scope, OAuth2Scope> {

    void updateScopePermission(OAuth2ScopeDTO scopeDTO);

    OAuth2Scope getByScopeCode(String scopeCode);

    List<OAuth2Scope> getByScopeCodes(List<String> scopeCodes);

}
