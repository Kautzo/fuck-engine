package cn.fuck.engine.oauth2.management.manager;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;

import java.util.List;

public interface OAuth2ScopeManager extends BaseManager<OAuth2Scope> {
    OAuth2Scope getByScopeCode(String scopeCode);

    List<OAuth2Scope> getByScopeCodes(List<String> scopeCodes);
}
