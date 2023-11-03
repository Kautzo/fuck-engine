package cn.fuck.engine.oauth2.management.processor;

import cn.fuck.engine.oauth2.core.definition.domain.FuckGrantedAuthority;
import cn.fuck.engine.oauth2.core.definition.service.EnhanceClientDetailsService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationService;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>Description: 客户端交互处理器 </p>
 */
public class FuckClientDetailsService implements EnhanceClientDetailsService {

    private final OAuth2ApplicationService applicationService;
    private final OAuth2ScopePermissionService scopePermissionService;

    public FuckClientDetailsService(OAuth2ApplicationService applicationService,
                                    OAuth2ScopePermissionService scopePermissionService) {
        this.applicationService = applicationService;
        this.scopePermissionService = scopePermissionService;
    }

    @Override
    public Set<FuckGrantedAuthority> findAuthoritiesById(String clientId) {

        OAuth2Application application = applicationService.findByClientId(clientId);
        if (ObjectUtils.isNotEmpty(application)) {
            List<OAuth2Scope> scopes = application.getScopes();
            Set<FuckGrantedAuthority> result = new HashSet<>();
            if (CollectionUtils.isNotEmpty(scopes)) {
                List<String> scopeIds = scopes.stream().map(OAuth2Scope::getId).toList();
                List<OAuth2ScopePermission> scopePermissionList = scopePermissionService.getByScopeIds(scopeIds);
                if (CollectionUtils.isNotEmpty(scopePermissionList)) {
                    Set<FuckGrantedAuthority> grantedAuthorities = scopePermissionList.stream()
                            .map(item -> new FuckGrantedAuthority(item.getPermissionCode()))
                            .collect(Collectors.toSet());
                    result.addAll(grantedAuthorities);
                }
            }
            return result;
        }

        return new HashSet<>();
    }
}
