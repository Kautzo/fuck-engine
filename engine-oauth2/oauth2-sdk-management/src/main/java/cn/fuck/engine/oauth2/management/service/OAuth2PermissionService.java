package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.repository.OAuth2PermissionRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Permission;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2PermissionService </p>
 * @date : 2022/4/1 13:53
 */
@Service
public class OAuth2PermissionService extends BaseService<OAuth2Permission, String> {

    private final OAuth2PermissionRepository authorityRepository;

    public OAuth2PermissionService(OAuth2PermissionRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public BaseRepository<OAuth2Permission, String> getRepository() {
        return authorityRepository;
    }
}
