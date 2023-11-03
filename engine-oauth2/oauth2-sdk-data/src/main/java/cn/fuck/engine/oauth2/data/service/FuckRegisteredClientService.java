package cn.fuck.engine.oauth2.data.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;

import java.util.Optional;

public interface FuckRegisteredClientService extends BaseService<FuckRegisteredClient> {

    Optional<FuckRegisteredClient> getByClientId(String clientId);

}
