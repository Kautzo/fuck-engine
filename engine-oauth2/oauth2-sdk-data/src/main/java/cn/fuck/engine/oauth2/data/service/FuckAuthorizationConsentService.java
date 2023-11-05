package cn.fuck.engine.oauth2.data.service;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Optional;

public interface FuckAuthorizationConsentService
        extends BaseService<FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent> {

    Optional<FuckAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

}
