package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.supplier.upms.logic.repository.security.SysRoleRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysPermission;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: SysRoleService </p>
 * @date : 2019/11/25 11:07
 */
@Service
public class SysRoleService extends BaseService<SysRole, String> {

    private final SysRoleRepository sysRoleRepository;

    public SysRoleService(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    @Override
    public BaseRepository<SysRole, String> getRepository() {
        return this.sysRoleRepository;
    }

    public SysRole assign(String roleId, String[] permissions) {

        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permission : permissions) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(permission);
            sysPermissions.add(sysPermission);
        }

        SysRole sysRole = findById(roleId);
        sysRole.setPermissions(sysPermissions);

        return saveAndFlush(sysRole);
    }

    public SysRole findByRoleCode(String roleCode) {
        return sysRoleRepository.findByRoleCode(roleCode);
    }

    public SysRole findByRoleId(String roleId) {
        return sysRoleRepository.findByRoleId(roleId);
    }
}
