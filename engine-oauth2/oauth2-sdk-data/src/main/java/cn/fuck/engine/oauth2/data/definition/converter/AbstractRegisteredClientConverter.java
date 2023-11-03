package cn.fuck.engine.oauth2.data.definition.converter;

import cn.fuck.engine.oauth2.core.definition.domain.RegisteredClientDetails;
import cn.fuck.engine.oauth2.data.jackson2.OAuth2JacksonProcessor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * <p>Description: RegisteredClient 转换器</p>
 */
public abstract class AbstractRegisteredClientConverter<S extends RegisteredClientDetails> extends AbstractOAuth2EntityConverter<S, RegisteredClient> implements RegisteredClientConverter<S> {

    public AbstractRegisteredClientConverter(OAuth2JacksonProcessor jacksonProcessor) {
        super(jacksonProcessor);
    }

    @Override
    public RegisteredClient convert(S details) {
        return RegisteredClientConverter.super.convert(details);
    }
}
