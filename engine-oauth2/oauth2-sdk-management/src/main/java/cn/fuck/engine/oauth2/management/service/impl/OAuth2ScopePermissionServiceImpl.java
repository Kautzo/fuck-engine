package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ScopePermissionMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OAuth2ScopePermissionServiceImpl extends ServiceImpl<OAuth2ScopePermissionMapper, OAuth2ScopePermission> implements OAuth2ScopePermissionService {
    @Override
    public List<OAuth2ScopePermission> getByScopeId(String scopeId) {
        return list(Wrappers.lambdaQuery(OAuth2ScopePermission.class).eq(OAuth2ScopePermission::getScopeId, scopeId));
    }

    @Override
    public List<OAuth2ScopePermission> getByScopeIds(List<String> scopeId) {
        return list(Wrappers.lambdaQuery(OAuth2ScopePermission.class).in(OAuth2ScopePermission::getScopeId, scopeId));
    }
}
