package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.oauth2.management.manager.OAuth2ApplicationScopeManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ApplicationScopeMapper;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ApplicationScopeManagerImpl extends BaseManagerImpl<OAuth2ApplicationScopeMapper, OAuth2ApplicationScope> implements OAuth2ApplicationScopeManager {
}
