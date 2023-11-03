package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.management.dto.OAuth2ScopeDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;

import java.util.List;

public interface OAuth2ScopeService extends BaseService<OAuth2Scope> {
    void updateScopePermission(OAuth2ScopeDTO scopeDTO);

    OAuth2Scope getByScopeCode(String scopeCode);

    Boolean handlerDelete(List<String> ids);
}
