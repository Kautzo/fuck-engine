package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.oauth2.management.manager.OAuth2ScopePermissionManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ScopePermissionMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopePermissionService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class OAuth2ScopePermissionServiceImpl
        extends BaseServiceImpl<OAuth2ScopePermissionManager, OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission, OAuth2ScopePermission>
        implements OAuth2ScopePermissionService {
    @Override
    public List<OAuth2ScopePermission> getByScopeId(String scopeId) {
        return baseManger.list(Wrappers.lambdaQuery(OAuth2ScopePermission.class)
                .eq(OAuth2ScopePermission::getScopeId, scopeId));
    }

    @Override
    public List<OAuth2ScopePermission> getByScopeIds(List<String> scopeId) {
        return baseManger.list(Wrappers.lambdaQuery(OAuth2ScopePermission.class)
                .in(OAuth2ScopePermission::getScopeId, scopeId));
    }

    @Override
    public void removeByScopeIds(Collection<String> scopeIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2ScopePermission.class)
                .in(OAuth2ScopePermission::getScopeId, scopeIds));
    }

    @Override
    public void removeByPermissionIds(Collection<String> permissionIds) {
        baseManger.remove(Wrappers.lambdaQuery(OAuth2ScopePermission.class)
                .in(OAuth2ScopePermission::getPermissionId, permissionIds));
    }
}
