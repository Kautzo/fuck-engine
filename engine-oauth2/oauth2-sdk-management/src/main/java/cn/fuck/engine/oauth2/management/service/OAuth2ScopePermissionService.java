package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OAuth2ScopePermissionService extends IService<OAuth2ScopePermission> {
    List<OAuth2ScopePermission> getByScopeId(String scopeId);
    List<OAuth2ScopePermission> getByScopeIds(List<String> scopeId);
}
