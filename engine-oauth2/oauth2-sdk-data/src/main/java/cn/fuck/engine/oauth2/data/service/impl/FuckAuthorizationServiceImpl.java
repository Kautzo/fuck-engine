package cn.fuck.engine.oauth2.data.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.oauth2.data.entity.FuckAuthorization;
import cn.fuck.engine.oauth2.data.mapper.FuckAuthorizationMapper;
import cn.fuck.engine.oauth2.data.service.FuckAuthorizationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Description: FuckAuthorizationService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Slf4j
@Service
public class FuckAuthorizationServiceImpl extends BaseServiceImpl<FuckAuthorizationMapper, FuckAuthorization> implements FuckAuthorizationService {

    @Override
    public Optional<FuckAuthorization> findByState(String state) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getState, state)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByState.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByAuthorizationCode(String authorizationCode) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getAuthorizationCodeValue, authorizationCode)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByAuthorizationCode.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByAccessToken(String accessToken) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getAccessTokenValue, accessToken)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByAccessToken.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByRefreshToken(String refreshToken) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getRefreshTokenValue, refreshToken)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByRefreshToken.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByOidcIdTokenValue(String idToken) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getOidcIdTokenValue, idToken)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByOidcIdTokenValue.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByUserCodeValue(String userCode) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getUserCodeValue, userCode)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByUserCodeValue.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByDeviceCodeValue(String deviceCode) {
        Optional<FuckAuthorization> result = getOneOpt(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getDeviceCodeValue, deviceCode)
                .last("LIMIT 1"));
        log.debug("[FUCK] |- FuckAuthorizationService Service findByDeviceCodeValue.");
        return result;
    }

    @Override
    public Optional<FuckAuthorization> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token) {
        LambdaQueryWrapper<FuckAuthorization> queryWrapper = Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getState, token)
                .or()
                .eq(FuckAuthorization::getAuthorizationCodeValue, token)
                .or()
                .eq(FuckAuthorization::getAccessTokenValue, token)
                .or()
                .eq(FuckAuthorization::getRefreshTokenValue, token)
                .or()
                .eq(FuckAuthorization::getOidcIdTokenValue, token)
                .or()
                .eq(FuckAuthorization::getUserCodeValue, token)
                .or()
                .eq(FuckAuthorization::getDeviceCodeValue, token)
                .last("LIMIT 1");
        Optional<FuckAuthorization> result = getOneOpt(queryWrapper);
        log.trace("[FUCK] |- FuckAuthorization Service findByDetection.");
        return result;
    }

    @Override
    public void clearHistoryToken() {
        remove(Wrappers.lambdaQuery(FuckAuthorization.class)
                .lt(FuckAuthorization::getRefreshTokenExpiresAt, LocalDateTime.now()));
        log.debug("[FUCK] |- FuckAuthorizationService Service clearExpireAccessToken.");
    }

    @Override
    public List<FuckAuthorization> findAvailableAuthorizations(String registeredClientId, String principalName) {
        List<FuckAuthorization> authorizations = list(Wrappers.lambdaQuery(FuckAuthorization.class)
                .eq(FuckAuthorization::getRegisteredClientId, registeredClientId)
                .eq(FuckAuthorization::getPrincipalName, principalName)
                .ge(FuckAuthorization::getAccessTokenExpiresAt, LocalDateTime.now()));
        log.debug("[FUCK] |- FuckAuthorizationService Service findAvailableAuthorizations.");
        return authorizations;
    }

    @Override
    public int findAuthorizationCount(String registeredClientId, String principalName) {
        List<FuckAuthorization> authorizations = findAvailableAuthorizations(registeredClientId, principalName);
        int count = 0;
        if (CollectionUtils.isNotEmpty(authorizations)) {
            count = authorizations.size();
        }
        log.debug("[FUCK] |- FuckAuthorizationService Service current authorization count is [{}].", count);
        return count;
    }
}
