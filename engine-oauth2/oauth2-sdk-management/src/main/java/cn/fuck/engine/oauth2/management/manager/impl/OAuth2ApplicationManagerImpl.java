package cn.fuck.engine.oauth2.management.manager.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import cn.fuck.engine.oauth2.management.manager.OAuth2ApplicationManager;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ApplicationMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ApplicationManagerImpl extends BaseManagerImpl<OAuth2ApplicationMapper, OAuth2Application> implements OAuth2ApplicationManager {
    @Override
    public OAuth2Application getByClientId(String clientId) {
        return getOne(Wrappers.lambdaQuery(OAuth2Application.class)
                .eq(OAuth2Application::getClientId, clientId));
    }
}
