package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OAuth2ApplicationScopeService extends IService<OAuth2ApplicationScope> {
    List<OAuth2ApplicationScope> getByApplicationId(String applicationId);
}
