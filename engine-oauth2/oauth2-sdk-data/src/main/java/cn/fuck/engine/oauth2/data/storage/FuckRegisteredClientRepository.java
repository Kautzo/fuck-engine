package cn.fuck.engine.oauth2.data.storage;

import cn.fuck.engine.oauth2.data.converter.FuckToOAuth2RegisteredClientConverter;
import cn.fuck.engine.oauth2.data.converter.OAuth2ToFuckRegisteredClientConverter;
import cn.fuck.engine.oauth2.data.jackson2.OAuth2JacksonProcessor;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import cn.fuck.engine.oauth2.data.service.impl.FuckRegisteredClientServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.Collection;
import java.util.Collections;

/**
 * <p>Description: RegisteredClient服务 </p>
 */
public class FuckRegisteredClientRepository implements RegisteredClientRepository {

    private final FuckRegisteredClientServiceImpl fuckRegisteredClientServiceImpl;
    private final Converter<FuckRegisteredClient, RegisteredClient> fuckToOAuth2Converter;
    private final Converter<RegisteredClient, FuckRegisteredClient> oauth2ToFuckConverter;

    public FuckRegisteredClientRepository(FuckRegisteredClientServiceImpl fuckRegisteredClientServiceImpl, PasswordEncoder passwordEncoder) {
        this.fuckRegisteredClientServiceImpl = fuckRegisteredClientServiceImpl;
        OAuth2JacksonProcessor jacksonProcessor = new OAuth2JacksonProcessor();
        this.fuckToOAuth2Converter = new FuckToOAuth2RegisteredClientConverter(jacksonProcessor);
        this.oauth2ToFuckConverter = new OAuth2ToFuckRegisteredClientConverter(jacksonProcessor, passwordEncoder);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        fuckRegisteredClientServiceImpl.save(toEntity(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        FuckRegisteredClient fuckRegisteredClient = fuckRegisteredClientServiceImpl.getById(id);
        if (ObjectUtils.isNotEmpty(fuckRegisteredClient)) {
            return toObject(fuckRegisteredClient);
        }
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return fuckRegisteredClientServiceImpl.getByClientId(clientId).map(this::toObject).orElse(null);
    }

    public void removeById(String id) {
        fuckRegisteredClientServiceImpl.removeByIds(Collections.singleton(id));
    }

    public void removeByIds(Collection<String> ids) {
        fuckRegisteredClientServiceImpl.removeByIds(ids);
    }

    private RegisteredClient toObject(FuckRegisteredClient fuckRegisteredClient) {
        return fuckToOAuth2Converter.convert(fuckRegisteredClient);
    }

    private FuckRegisteredClient toEntity(RegisteredClient registeredClient) {
        return oauth2ToFuckConverter.convert(registeredClient);
    }
}
