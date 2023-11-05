package cn.fuck.engine.oauth2.data.service.impl;

import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import cn.fuck.engine.oauth2.data.manager.FuckAuthorizationConsentManager;
import cn.fuck.engine.oauth2.data.service.FuckAuthorizationConsentService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Description: FuckAuthorizationConsentService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Slf4j
@Service
public class FuckAuthorizationConsentServiceImpl
        extends BaseServiceImpl<FuckAuthorizationConsentManager, FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent, FuckAuthorizationConsent>
        implements FuckAuthorizationConsentService {

    @Override
    public Optional<FuckAuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        Optional<FuckAuthorizationConsent> result = baseManger.getOneOpt(Wrappers.lambdaQuery(FuckAuthorizationConsent.class)
                .eq(FuckAuthorizationConsent::getRegisteredClientId, registeredClientId)
                .eq(FuckAuthorizationConsent::getPrincipalName, principalName)
                .last("LIMIT 1"));
        log.trace("[FUCK] |- FuckAuthorizationConsentService Service findByRegisteredClientIdAndPrincipalName.");
        return result;
    }

    @Override
    public void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName) {
        baseManger.remove(Wrappers.lambdaQuery(FuckAuthorizationConsent.class)
                .eq(FuckAuthorizationConsent::getRegisteredClientId, registeredClientId)
                .eq(FuckAuthorizationConsent::getPrincipalName, principalName));
        log.trace("[FUCK] |- FuckAuthorizationConsentService Service deleteByRegisteredClientIdAndPrincipalName.");
    }
}
