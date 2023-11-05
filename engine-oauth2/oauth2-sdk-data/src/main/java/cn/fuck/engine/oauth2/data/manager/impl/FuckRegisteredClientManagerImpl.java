package cn.fuck.engine.oauth2.data.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.data.entity.FuckRegisteredClient;
import cn.fuck.engine.oauth2.data.manager.FuckRegisteredClientManager;
import cn.fuck.engine.oauth2.data.mapper.FuckRegisteredClientMapper;
import org.springframework.stereotype.Service;

@Service
public class FuckRegisteredClientManagerImpl extends BaseManagerImpl<FuckRegisteredClientMapper, FuckRegisteredClient> implements FuckRegisteredClientManager {
}
