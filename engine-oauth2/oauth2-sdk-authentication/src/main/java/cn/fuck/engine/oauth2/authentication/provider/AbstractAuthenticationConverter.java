package cn.fuck.engine.oauth2.authentication.provider;

import cn.fuck.engine.assistant.core.utils.type.ListUtils;
import cn.fuck.engine.oauth2.authentication.utils.OAuth2EndpointUtils;
import cn.fuck.engine.oauth2.core.constants.OAuth2ErrorKeys;
import cn.fuck.engine.rest.core.exception.SessionInvalidException;
import cn.fuck.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.List;

/**
 * <p>Description: 抽象的认证 Converter </p>
 * @date : 2023/6/21 6:23
 */
public abstract class AbstractAuthenticationConverter implements AuthenticationConverter {

    private final HttpCryptoProcessor httpCryptoProcessor;

    public AbstractAuthenticationConverter(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    protected String[] decrypt(String sessionId, List<String> parameters) {
        if (StringUtils.isNotBlank(sessionId) && CollectionUtils.isNotEmpty(parameters)) {
            List<String> result = parameters.stream().map(item -> decrypt(sessionId, item)).toList();
            return ListUtils.toStringArray(result);
        }

        return ListUtils.toStringArray(parameters);
    }

    protected String decrypt(String sessionId, String parameter) {
        if (StringUtils.isNotBlank(sessionId) && StringUtils.isNotBlank(parameter)) {
            try {
                return httpCryptoProcessor.decrypt(sessionId, parameter);
            } catch (SessionInvalidException e) {
                OAuth2EndpointUtils.throwError(
                        OAuth2ErrorKeys.SESSION_EXPIRED,
                        e.getMessage(),
                        OAuth2EndpointUtils.ACCESS_TOKEN_REQUEST_ERROR_URI);
            }
        }
        return parameter;
    }
}
