package cn.fuck.engine.oauth2.data.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorization;

import java.util.List;
import java.util.Optional;

public interface FuckAuthorizationService extends BaseService<FuckAuthorization> {

    Optional<FuckAuthorization> findByState(String state);
    Optional<FuckAuthorization> findByAuthorizationCode(String authorizationCode);
    Optional<FuckAuthorization> findByAccessToken(String accessToken);
    Optional<FuckAuthorization> findByRefreshToken(String refreshToken);
    Optional<FuckAuthorization> findByOidcIdTokenValue(String idToken);
    Optional<FuckAuthorization> findByUserCodeValue(String userCode);
    Optional<FuckAuthorization> findByDeviceCodeValue(String deviceCode);
    void clearHistoryToken();
    List<FuckAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName);
    int findAuthorizationCount(String registeredClientId, String principalName);
    Optional<FuckAuthorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token);


}
