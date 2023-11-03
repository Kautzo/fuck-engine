package cn.fuck.engine.oauth2.data.storage;

import cn.fuck.engine.oauth2.data.converter.FuckToOAuth2AuthorizationConsentConverter;
import cn.fuck.engine.oauth2.data.converter.OAuth2ToFuckAuthorizationConsentConverter;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import cn.fuck.engine.oauth2.data.service.FuckAuthorizationConsentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * <p>Description: OAuth2 认证服务 </p>
 */
public class FuckOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {

    private final FuckAuthorizationConsentService fuckAuthorizationConsentService;
    private final Converter<FuckAuthorizationConsent, OAuth2AuthorizationConsent> fuckToOAuth2Converter;
    private final Converter<OAuth2AuthorizationConsent, FuckAuthorizationConsent> oauth2ToFuckConverter;

    public FuckOAuth2AuthorizationConsentService(FuckAuthorizationConsentService fuckAuthorizationConsentService,
                                                 RegisteredClientRepository registeredClientRepository) {
        this.fuckAuthorizationConsentService = fuckAuthorizationConsentService;
        this.fuckToOAuth2Converter = new FuckToOAuth2AuthorizationConsentConverter(registeredClientRepository);
        this.oauth2ToFuckConverter = new OAuth2ToFuckAuthorizationConsentConverter();
    }

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        this.fuckAuthorizationConsentService.save(toEntity(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        this.fuckAuthorizationConsentService.deleteByRegisteredClientIdAndPrincipalName(
                authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return this.fuckAuthorizationConsentService.findByRegisteredClientIdAndPrincipalName(
                registeredClientId, principalName).map(this::toObject).orElse(null);
    }

    private OAuth2AuthorizationConsent toObject(FuckAuthorizationConsent authorizationConsent) {
        return fuckToOAuth2Converter.convert(authorizationConsent);
    }

    private FuckAuthorizationConsent toEntity(OAuth2AuthorizationConsent authorizationConsent) {
        return oauth2ToFuckConverter.convert(authorizationConsent);
    }
}
