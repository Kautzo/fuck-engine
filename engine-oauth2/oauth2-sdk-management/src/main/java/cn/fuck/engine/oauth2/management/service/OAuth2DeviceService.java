package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.dto.OAuth2DeviceDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;
import cn.fuck.engine.rest.core.service.BaseService;
import org.springframework.security.oauth2.server.authorization.oidc.OidcClientRegistration;

import java.util.Set;

public interface OAuth2DeviceService
        extends BaseService<OAuth2Device, OAuth2DeviceDTO, OAuth2DeviceDTO, OAuth2Device, OAuth2Device> {

    boolean sync(OidcClientRegistration oidcClientRegistration);

    boolean activate(String clientId, boolean isActivated);

    void updateScope(String deviceId, Set<String> scopeIds);
}
