package cn.fuck.engine.oauth2.data.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;

import java.util.Optional;

public interface FuckAuthorizationConsentService extends BaseService<FuckAuthorizationConsent> {

    Optional<FuckAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

}
