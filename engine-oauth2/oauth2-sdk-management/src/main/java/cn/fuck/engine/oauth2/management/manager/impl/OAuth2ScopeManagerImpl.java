package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.manager.OAuth2ScopeManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ScopeMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OAuth2ScopeManagerImpl extends BaseManagerImpl<OAuth2ScopeMapper, OAuth2Scope> implements OAuth2ScopeManager {
    @Override
    public OAuth2Scope getByScopeCode(String scopeCode) {
        return getOne(Wrappers.lambdaQuery(OAuth2Scope.class)
                .eq(OAuth2Scope::getScopeCode, scopeCode)
                .last("LIMIT 1"));
    }

    @Override
    public List<OAuth2Scope> getByScopeCodes(List<String> scopeCodes) {
        return list(Wrappers.lambdaQuery(OAuth2Scope.class)
                .in(OAuth2Scope::getScopeCode, scopeCodes));
    }
}
