package cn.fuck.engine.oauth2.data.converter;

import cn.fuck.engine.oauth2.data.entity.FuckAuthorizationConsent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: OAuth2AuthorizationConsent 转 FuckAuthorizationConsent 转换器</p>
 */
public class OAuth2ToFuckAuthorizationConsentConverter implements Converter<OAuth2AuthorizationConsent, FuckAuthorizationConsent> {
    @Override
    public FuckAuthorizationConsent convert(OAuth2AuthorizationConsent authorizationConsent) {
        FuckAuthorizationConsent entity = new FuckAuthorizationConsent();
        entity.setRegisteredClientId(authorizationConsent.getRegisteredClientId());
        entity.setPrincipalName(authorizationConsent.getPrincipalName());

        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : authorizationConsent.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        entity.setAuthorities(StringUtils.collectionToCommaDelimitedString(authorities));

        return entity;
    }
}
