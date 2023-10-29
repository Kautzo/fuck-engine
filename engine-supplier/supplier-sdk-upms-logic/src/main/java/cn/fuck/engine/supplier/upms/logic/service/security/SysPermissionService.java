package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.supplier.upms.logic.repository.security.SysPermissionRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysPermission;
import org.springframework.stereotype.Service;

/**
 * <p>Description: SysPermissionService </p>
 * @date : 2019/11/25 16:11
 */
@Service
public class SysPermissionService extends BaseService<SysPermission, String> {

    private final SysPermissionRepository sysPermissionRepository;

    public SysPermissionService(SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionRepository = sysPermissionRepository;
    }

    @Override
    public BaseRepository<SysPermission, String> getRepository() {
        return this.sysPermissionRepository;
    }
}
