package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ApplicationScopeMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OAuth2ApplicationScopeServiceImpl extends ServiceImpl<OAuth2ApplicationScopeMapper, OAuth2ApplicationScope> implements OAuth2ApplicationScopeService {
    @Override
    public List<OAuth2ApplicationScope> getByApplicationId(String applicationId) {
        return list(Wrappers.lambdaQuery(OAuth2ApplicationScope.class).eq(OAuth2ApplicationScope::getApplicationId, applicationId));
    }
}
