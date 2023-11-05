package cn.fuck.engine.oauth2.data.service;

import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import cn.fuck.engine.rest.core.service.BaseService;

import java.util.Optional;

public interface FuckRegisteredClientService
        extends BaseService<FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient> {

    Optional<FuckRegisteredClient> getByClientId(String clientId);

}
