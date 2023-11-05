package cn.fuck.engine.oauth2.management.service.impl;

import cn.fuck.engine.oauth2.data.storage.FuckRegisteredClientRepository;
import cn.fuck.engine.oauth2.management.converter.OAuth2DeviceToRegisteredClientConverter;
import cn.fuck.engine.oauth2.management.converter.RegisteredClientToOAuth2DeviceConverter;
import cn.fuck.engine.oauth2.management.dto.OAuth2DeviceDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;
import cn.fuck.engine.oauth2.management.entity.OAuth2DeviceScope;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.manager.OAuth2DeviceManager;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceScopeService;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceService;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopeService;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class OAuth2DeviceServiceImpl
        extends BaseServiceImpl<OAuth2DeviceManager, OAuth2Device, OAuth2DeviceDTO, OAuth2DeviceDTO, OAuth2Device, OAuth2Device>
        implements OAuth2DeviceService {

    @Autowired
    private OAuth2ScopeService scopeService;
    @Autowired
    private OAuth2DeviceScopeService deviceScopeService;
    private final RegisteredClientRepository registeredClientRepository;
    private final FuckRegisteredClientRepository fuckRegisteredClientRepository;
    private final Converter<OAuth2Device, RegisteredClient> oauth2DeviceToRegisteredClientConverter;
    private final Converter<RegisteredClient, OAuth2Device> registeredClientToOAuth2DeviceConverter;

    public OAuth2DeviceServiceImpl(RegisteredClientRepository registeredClientRepository,
                                   FuckRegisteredClientRepository fuckRegisteredClientRepository,
                                   OAuth2ScopeService scopeService) {
        this.registeredClientRepository = registeredClientRepository;
        this.fuckRegisteredClientRepository = fuckRegisteredClientRepository;
        this.oauth2DeviceToRegisteredClientConverter = new OAuth2DeviceToRegisteredClientConverter();
        this.registeredClientToOAuth2DeviceConverter = new RegisteredClientToOAuth2DeviceConverter(scopeService);
    }


    /**
     * 客户端自动注册是将信息存储在 oauth2_registered_client 中。
     * 为了方便管理，将该条数据同步至 oauth2_device 表中。
     *
     * @param oidcClientRegistration {@link OidcClientRegistration}
     * @return 是否同步成功
     */
    @Override
    public boolean sync(OidcClientRegistration oidcClientRegistration) {
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(oidcClientRegistration.getClientId());

        if (ObjectUtils.isNotEmpty(registeredClient)) {
            OAuth2Device oauth2Device = registeredClientToOAuth2DeviceConverter.convert(registeredClient);
            if (ObjectUtils.isNotEmpty(oauth2Device)) {
                return baseManger.save(oauth2Device);
            }
        }
        return false;
    }

    @Override
    public boolean activate(String clientId, boolean isActivated) {
        return baseManger.activate(clientId, isActivated);
    }

    @Override
    @Transactional
    public void updateScope(String deviceId, Set<String> scopeIds) {
        List<OAuth2DeviceScope> dbPermissionList = deviceScopeService.getByDeviceId(deviceId);

        List<OAuth2DeviceScope> saveList = new ArrayList<>();
        List<OAuth2DeviceScope> removeList = new ArrayList<>();

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<OAuth2DeviceScope> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(scopeIds, item.getScopeId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(OAuth2DeviceScope::getScopeId).toList();
            List<String> save = scopeIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String scopeId : save) {
                    OAuth2DeviceScope deviceScope = new OAuth2DeviceScope();
                    deviceScope.setDeviceId(deviceId);
                    deviceScope.setScopeId(scopeId);
                    saveList.add(deviceScope);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(scopeIds)) {
                for (String scopeId : scopeIds) {
                    OAuth2DeviceScope deviceScope = new OAuth2DeviceScope();
                    deviceScope.setDeviceId(deviceId);
                    deviceScope.setScopeId(scopeId);
                    saveList.add(deviceScope);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            deviceScopeService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(OAuth2DeviceScope::getId).toList();
            deviceScopeService.removeByIds(removeIds);
        }
    }

    @Override
    @Transactional
    public OAuth2Device save(OAuth2DeviceDTO oAuth2DeviceDTO) {
        OAuth2Device device = super.save(oAuth2DeviceDTO);
        updateScope(device.getId(), oAuth2DeviceDTO.getScopeIds());
        return device;
    }

    @Override
    @Transactional
    public OAuth2Device updateById(OAuth2DeviceDTO oAuth2DeviceDTO) {
        OAuth2Device device = super.updateById(oAuth2DeviceDTO);
        updateScope(device.getId(), oAuth2DeviceDTO.getScopeIds());
        return device;
    }

    @Override
    public OAuth2Device getById(String id) {
        OAuth2Device device = super.getById(id);
        List<OAuth2DeviceScope> deviceScopeList = deviceScopeService.getByDeviceId(device.getId());
        if (CollUtil.isNotEmpty(deviceScopeList)) {
            List<String> scopeIds = deviceScopeList.stream().map(OAuth2DeviceScope::getScopeId).toList();
            List<OAuth2Scope> oAuth2Scopes = scopeService.listByIds(scopeIds);
            device.setScopes(oAuth2Scopes);
        }
        return device;
    }

    @Override
    public boolean removeByIds(Collection<String> idList) {
        super.removeByIds(idList);
        deviceScopeService.removeByDeviceIds(idList);
        return Boolean.TRUE;
    }
}
