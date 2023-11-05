package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.oauth2.management.manager.OAuth2ScopePermissionManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ScopePermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ScopePermissionManagerImpl extends BaseManagerImpl<OAuth2ScopePermissionMapper, OAuth2ScopePermission> implements OAuth2ScopePermissionManager {
}
