package cn.fuck.engine.oauth2.data.service.impl;

import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import cn.fuck.engine.oauth2.data.manager.FuckRegisteredClientManager;
import cn.fuck.engine.oauth2.data.service.FuckRegisteredClientService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * <p>Description: FuckRegisteredClientService </p>
 * <p>
 * 这里命名没有按照统一的习惯，主要是为了防止与 spring-authorization-server 已有类的同名而导致Bean注入失败
 */
@Slf4j
@Service
public class FuckRegisteredClientServiceImpl
        extends BaseServiceImpl<FuckRegisteredClientManager, FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient, FuckRegisteredClient>
        implements FuckRegisteredClientService {

    @Override
    public Optional<FuckRegisteredClient> getByClientId(String clientId) {
        return baseManger.getOneOpt(Wrappers.lambdaQuery(FuckRegisteredClient.class)
                .eq(FuckRegisteredClient::getClientId, clientId)
                .last("LIMIT 1"));
    }
}
