package cn.fuck.engine.oauth2.data.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import cn.fuck.engine.oauth2.data.manager.FuckAuthorizationConsentManager;
import cn.fuck.engine.oauth2.data.mapper.FuckAuthorizationConsentMapper;
import org.springframework.stereotype.Service;

@Service
public class FuckAuthorizationConsentManagerImpl extends BaseManagerImpl<FuckAuthorizationConsentMapper, FuckAuthorizationConsent> implements FuckAuthorizationConsentManager {
}
