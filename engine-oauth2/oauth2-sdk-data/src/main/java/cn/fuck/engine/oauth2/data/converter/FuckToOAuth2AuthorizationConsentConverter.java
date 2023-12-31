package cn.fuck.engine.oauth2.data.converter;

import cn.fuck.engine.oauth2.core.definition.domain.FuckGrantedAuthority;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.util.StringUtils;

/**
 * <p>Description: FuckAuthorizationConsent 转 OAuth2AuthorizationConsent 转换器 </p>
 */
public class FuckToOAuth2AuthorizationConsentConverter implements Converter<FuckAuthorizationConsent, OAuth2AuthorizationConsent> {

    private final RegisteredClientRepository registeredClientRepository;

    public FuckToOAuth2AuthorizationConsentConverter(RegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Override
    public OAuth2AuthorizationConsent convert(FuckAuthorizationConsent authorizationConsent) {
        String registeredClientId = authorizationConsent.getRegisteredClientId();
        RegisteredClient registeredClient = this.registeredClientRepository.findById(registeredClientId);
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.");
        }

        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(
                registeredClientId, authorizationConsent.getPrincipalName());
        if (authorizationConsent.getAuthorities() != null) {
            for (String authority : StringUtils.commaDelimitedListToSet(authorizationConsent.getAuthorities())) {
                builder.authority(new FuckGrantedAuthority(authority));
            }
        }

        return builder.build();
    }
}
