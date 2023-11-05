package cn.fuck.engine.oauth2.data.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorization;
import cn.fuck.engine.oauth2.data.manager.FuckAuthorizationManager;
import cn.fuck.engine.oauth2.data.mapper.FuckAuthorizationMapper;
import org.springframework.stereotype.Service;

@Service
public class FuckAuthorizationManagerImpl extends BaseManagerImpl<FuckAuthorizationMapper, FuckAuthorization> implements FuckAuthorizationManager {
}
