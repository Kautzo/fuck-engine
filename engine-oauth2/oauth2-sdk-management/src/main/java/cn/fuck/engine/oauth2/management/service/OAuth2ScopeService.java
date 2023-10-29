package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.repository.OAuth2ScopeRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Permission;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p> Description : OauthScopeService </p>
 * @date : 2020/3/19 17:00
 */
@Service
public class OAuth2ScopeService extends BaseService<OAuth2Scope, String> {

    private final OAuth2ScopeRepository oauthScopesRepository;

    public OAuth2ScopeService(OAuth2ScopeRepository oauthScopesRepository) {
        this.oauthScopesRepository = oauthScopesRepository;
    }

    @Override
    public BaseRepository<OAuth2Scope, String> getRepository() {
        return oauthScopesRepository;
    }

    public OAuth2Scope assigned(String scopeId, Set<OAuth2Permission> permissions) {

        OAuth2Scope oldScope = findById(scopeId);
        oldScope.setPermissions(permissions);

        return saveAndFlush(oldScope);
    }

    public OAuth2Scope findByScopeCode(String scopeCode) {
        return oauthScopesRepository.findByScopeCode(scopeCode);
    }

    public List<OAuth2Scope> findByScopeCodeIn(List<String> scopeCodes) {
        return oauthScopesRepository.findByScopeCodeIn(scopeCodes);
    }
}
