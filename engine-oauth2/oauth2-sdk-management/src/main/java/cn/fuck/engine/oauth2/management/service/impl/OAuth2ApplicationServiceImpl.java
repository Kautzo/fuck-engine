package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.oauth2.data.storage.FuckRegisteredClientRepository;
import cn.fuck.engine.oauth2.management.converter.OAuth2ApplicationToRegisteredClientConverter;
import cn.fuck.engine.oauth2.management.dto.OAuth2ApplicationDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import cn.fuck.engine.oauth2.management.entity.OAuth2ApplicationScope;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.mapper.OAuth2ApplicationMapper;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationScopeService;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationService;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: OAuth2ApplicationService </p>
 */
@Slf4j
@Service
public class OAuth2ApplicationServiceImpl extends BaseServiceImpl<OAuth2ApplicationMapper, OAuth2Application> implements OAuth2ApplicationService {

    @Autowired
    private OAuth2ScopeService scopeService;
    @Autowired
    private OAuth2ApplicationScopeService applicationScopeService;
    @Autowired
    private FuckRegisteredClientRepository fuckRegisteredClientRepository;
    private final Converter<OAuth2Application, RegisteredClient> objectConverter = new OAuth2ApplicationToRegisteredClientConverter();

    @Override
    @Transactional
    public void updateScope(String applicationId, Set<String> scopeIds) {
        List<OAuth2ApplicationScope> dbPermissionList = applicationScopeService.getByApplicationId(applicationId);

        List<OAuth2ApplicationScope> saveList = new ArrayList<>();
        List<OAuth2ApplicationScope> removeList = new ArrayList<>();

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<OAuth2ApplicationScope> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(scopeIds, item.getScopeId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(OAuth2ApplicationScope::getApplicationId).toList();
            List<String> save = scopeIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String scopeId : save) {
                    OAuth2ApplicationScope applicationScope = new OAuth2ApplicationScope();
                    applicationScope.setApplicationId(applicationId);
                    applicationScope.setScopeId(scopeId);
                    saveList.add(applicationScope);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(scopeIds)) {
                for (String scopeId : scopeIds) {
                    OAuth2ApplicationScope applicationScope = new OAuth2ApplicationScope();
                    applicationScope.setApplicationId(applicationId);
                    applicationScope.setScopeId(scopeId);
                    saveList.add(applicationScope);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            applicationScopeService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(OAuth2ApplicationScope::getId).toList();
            applicationScopeService.removeByIds(removeIds);
        }
    }

    @Override
    public OAuth2Application findById(String id) {
        OAuth2Application byId = super.findById(id);
        List<OAuth2ApplicationScope> applicationScopeList = applicationScopeService.list(Wrappers.lambdaQuery(OAuth2ApplicationScope.class)
                .eq(OAuth2ApplicationScope::getApplicationId, id));
        List<String> scopeIds = applicationScopeList.stream().map(OAuth2ApplicationScope::getScopeId).toList();
        List<OAuth2Scope> oAuth2Scopes = scopeService.listByIds(scopeIds);
        byId.setScopes(oAuth2Scopes);
        return byId;
    }

    @Override
    public OAuth2Application findByClientId(String clientId) {
        OAuth2Application application = getOne(Wrappers.lambdaQuery(OAuth2Application.class)
                .eq(OAuth2Application::getClientId, clientId));
        return findById(application.getId());
    }

    @Override
    @Transactional
    public void handlerSave(OAuth2ApplicationDTO oAuth2ApplicationDTO) {
        OAuth2Application application = BeanUtil.toBean(oAuth2ApplicationDTO, OAuth2Application.class);
        save(application);
        updateScope(application.getId(), oAuth2ApplicationDTO.getScopeIds());
    }

    @Override
    @Transactional
    public void handlerUpdate(OAuth2ApplicationDTO oAuth2ApplicationDTO) {
        OAuth2Application application = BeanUtil.toBean(oAuth2ApplicationDTO, OAuth2Application.class);
        updateById(application);
        updateScope(application.getId(), oAuth2ApplicationDTO.getScopeIds());
    }

    @Override
    @Transactional
    public Boolean handlerDelete(List<String> ids) {
        removeByIds(ids);
        fuckRegisteredClientRepository.removeByIds(ids);
        applicationScopeService.remove(Wrappers.lambdaQuery(OAuth2ApplicationScope.class)
                .in(OAuth2ApplicationScope::getApplicationId, ids));
        return Boolean.TRUE;
    }
}
