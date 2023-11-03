package cn.fuck.engine.oauth2.data.storage;

import cn.fuck.engine.oauth2.data.converter.FuckToOAuth2AuthorizationConverter;
import cn.fuck.engine.oauth2.data.converter.OAuth2ToFuckAuthorizationConverter;
import cn.fuck.engine.oauth2.data.jackson2.OAuth2JacksonProcessor;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorization;
import cn.fuck.engine.oauth2.data.service.FuckAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>Description: 基于 JPA 的 OAuth2 认证服务 </p>
 */
@Slf4j
public class FuckOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final FuckAuthorizationService fuckAuthorizationService;
    private final Converter<FuckAuthorization, OAuth2Authorization> fuckToOAuth2Converter;
    private final Converter<OAuth2Authorization, FuckAuthorization> oauth2ToFuckConverter;

    public FuckOAuth2AuthorizationService(FuckAuthorizationService fuckAuthorizationService, RegisteredClientRepository registeredClientRepository) {
        this.fuckAuthorizationService = fuckAuthorizationService;
        OAuth2JacksonProcessor jacksonProcessor = new OAuth2JacksonProcessor();
        this.fuckToOAuth2Converter = new FuckToOAuth2AuthorizationConverter(jacksonProcessor, registeredClientRepository);
        this.oauth2ToFuckConverter = new OAuth2ToFuckAuthorizationConverter(jacksonProcessor);

    }

    @Override
    public void save(OAuth2Authorization authorization) {
        this.fuckAuthorizationService.save(toEntity(authorization));
    }

    @Transactional
    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        this.fuckAuthorizationService.removeById(authorization.getId());
        log.debug("[FUCK] |- Jpa OAuth2 Authorization Service remove entity.");
        // TODO： 后期还是考虑改为异步任务的形式，先临时放在这里。
        this.fuckAuthorizationService.clearHistoryToken();
        log.debug("[FUCK] |- Jpa OAuth2 Authorization Service clear history token.");
    }

    @Override
    public OAuth2Authorization findById(String id) {
        FuckAuthorization fuckAuthorization = this.fuckAuthorizationService.getById(id);
        if (ObjectUtils.isNotEmpty(fuckAuthorization)) {
            return toObject(fuckAuthorization);
        } else {
            return null;
        }
    }

    public int findAuthorizationCount(String registeredClientId, String principalName) {
        int count = this.fuckAuthorizationService.findAuthorizationCount(registeredClientId, principalName);
        log.debug("[FUCK] |- Jpa OAuth2 Authorization Service findAuthorizationCount.");
        return count;
    }

    public List<OAuth2Authorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<FuckAuthorization> authorizations = this.fuckAuthorizationService.findAvailableAuthorizations(registeredClientId, principalName);
        if (CollectionUtils.isNotEmpty(authorizations)) {
            return authorizations.stream().map(this::toObject).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.hasText(token, "token cannot be empty");

        Optional<FuckAuthorization> result;

        if (tokenType == null) {
            result = this.fuckAuthorizationService.findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(token);
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByState(token);
        } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByAuthorizationCode(token);
        } else if (OAuth2ParameterNames.ACCESS_TOKEN.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByAccessToken(token);
        } else if (OAuth2ParameterNames.REFRESH_TOKEN.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByRefreshToken(token);
        } else if (OidcParameterNames.ID_TOKEN.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByOidcIdTokenValue(token);
        } else if (OAuth2ParameterNames.USER_CODE.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByUserCodeValue(token);
        } else if (OAuth2ParameterNames.DEVICE_CODE.equals(tokenType.getValue())) {
            result = this.fuckAuthorizationService.findByDeviceCodeValue(token);
        } else {
            result = Optional.empty();
        }

        return result.map(this::toObject).orElse(null);
    }

    private OAuth2Authorization toObject(FuckAuthorization entity) {
        return fuckToOAuth2Converter.convert(entity);
    }

    private FuckAuthorization toEntity(OAuth2Authorization authorization) {
        return oauth2ToFuckConverter.convert(authorization);
    }
}
