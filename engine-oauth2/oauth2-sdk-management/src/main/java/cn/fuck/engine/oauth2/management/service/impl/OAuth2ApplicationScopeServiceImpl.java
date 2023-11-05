package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.oauth2.management.manager.OAuth2ApplicationScopeManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ApplicationScopeMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationScopeService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class OAuth2ApplicationScopeServiceImpl
        extends BaseServiceImpl<OAuth2ApplicationScopeManager, OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope, OAuth2ApplicationScope>
        implements OAuth2ApplicationScopeService {

    @Override
    public List<OAuth2ApplicationScope> getByApplicationId(String applicationId) {
        return baseManger.list(Wrappers.lambdaQuery(OAuth2ApplicationScope.class).eq(OAuth2ApplicationScope::getApplicationId, applicationId));
    }

    @Override
    public void removeByApplicationIds(Collection<String> applicationIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2ApplicationScope.class)
                .in(OAuth2ApplicationScope::getApplicationId, applicationIds));
    }

    @Override
    public void removeByScopeIds(Collection<String> scopeIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2ApplicationScope.class)
               .in(OAuth2ApplicationScope::getScopeId, scopeIds));
    }

}
