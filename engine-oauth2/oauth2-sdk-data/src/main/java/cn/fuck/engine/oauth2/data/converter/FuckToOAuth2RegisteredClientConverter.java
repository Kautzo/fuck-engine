package cn.fuck.engine.oauth2.data.converter;

import cn.fuck.engine.oauth2.data.jackson2.OAuth2JacksonProcessor;
import cn.fuck.engine.oauth2.data.definition.converter.AbstractRegisteredClientConverter;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * <p>Description: FuckRegisteredClient 转 换适配器 </p>
 */
public class FuckToOAuth2RegisteredClientConverter extends AbstractRegisteredClientConverter<FuckRegisteredClient> {

    public FuckToOAuth2RegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
        super(jacksonProcessor);
    }

    @Override
    public Set<String> getScopes(FuckRegisteredClient details) {
        return StringUtils.commaDelimitedListToSet(details.getScopes());
    }

    @Override
    public ClientSettings getClientSettings(FuckRegisteredClient details) {
        Map<String, Object> clientSettingsMap = parseMap(details.getClientSettings());
        return ClientSettings.withSettings(clientSettingsMap).build();
    }

    @Override
    public TokenSettings getTokenSettings(FuckRegisteredClient details) {
        Map<String, Object> tokenSettingsMap = parseMap(details.getTokenSettings());
        return TokenSettings.withSettings(tokenSettingsMap).build();
    }
}
