package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.oauth2.management.dto.OAuth2PermissionDTO;
import cn.fuck.engine.oauth2.management.dto.OAuth2ScopeDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.entity.OAuth2ScopePermission;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ScopeMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationScopeService;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopePermissionService;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p> Description : OauthScopeService </p>
 */
@Service
public class OAuth2ScopeServiceImpl extends BaseServiceImpl<OAuth2ScopeMapper, OAuth2Scope> implements OAuth2ScopeService {

    @Autowired
    private OAuth2ScopePermissionService scopePermissionService;
    @Autowired
    private OAuth2ApplicationScopeService applicationScopeService;

    @Override
    public void updateScopePermission(OAuth2ScopeDTO scopeDTO) {
        List<OAuth2ScopePermission> dbPermissionList = scopePermissionService.getByScopeId(scopeDTO.getScopeId());

        List<OAuth2ScopePermission> saveList = new ArrayList<>();
        List<OAuth2ScopePermission> removeList = new ArrayList<>();

        List<String> permissionIds = scopeDTO.getPermissions().stream().map(OAuth2PermissionDTO::getPermissionId).toList();
        Map<String, String> permissionMap = scopeDTO.getPermissions().stream().collect(Collectors.toMap(OAuth2PermissionDTO::getPermissionId, OAuth2PermissionDTO::getPermissionCode));

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<OAuth2ScopePermission> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(permissionIds, item.getPermissionId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(OAuth2ScopePermission::getPermissionId).toList();
            List<String> save = permissionIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String permissionId : save) {
                    OAuth2ScopePermission scopePermission = new OAuth2ScopePermission();
                    scopePermission.setScopeId(scopeDTO.getScopeId());
                    scopePermission.setPermissionId(permissionId);
                    scopePermission.setPermissionCode(permissionMap.get(permissionId));
                    saveList.add(scopePermission);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(permissionIds)) {
                for (String permissionId : permissionIds) {
                    OAuth2ScopePermission scopePermission = new OAuth2ScopePermission();
                    scopePermission.setScopeId(scopeDTO.getScopeId());
                    scopePermission.setPermissionId(permissionId);
                    scopePermission.setPermissionCode(permissionMap.get(permissionId));
                    saveList.add(scopePermission);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            scopePermissionService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(OAuth2ScopePermission::getId).toList();
            scopePermissionService.removeByIds(removeIds);
        }
    }

    @Override
    public OAuth2Scope getByScopeCode(String scopeCode) {
        return getOne(Wrappers.lambdaQuery(OAuth2Scope.class)
                .eq(OAuth2Scope::getScopeCode, scopeCode)
                .last("LIMIT 1"));
    }

    @Override
    @Transactional
    public Boolean handlerDelete(List<String> ids) {
        removeByIds(ids);
        scopePermissionService.remove(Wrappers.lambdaQuery(OAuth2ScopePermission.class)
                .in(OAuth2ScopePermission::getScopeId, ids));
        applicationScopeService.remove(Wrappers.lambdaQuery(OAuth2ApplicationScope.class)
                .in(OAuth2ApplicationScope::getScopeId, ids));
        return Boolean.TRUE;
    }
}
