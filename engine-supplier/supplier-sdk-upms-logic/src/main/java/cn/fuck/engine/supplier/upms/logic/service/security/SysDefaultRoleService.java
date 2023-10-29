package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.supplier.upms.logic.repository.security.SysDefaultRoleRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysDefaultRole;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 系统默认角色Service </p>
 * @date : 2021/7/21 16:09
 */
@Service
public class SysDefaultRoleService extends BaseService<SysDefaultRole, String> {

    private final SysDefaultRoleRepository sysDefaultRoleRepository;
    private final SysRoleService sysRoleService;

    public SysDefaultRoleService(SysDefaultRoleRepository sysDefaultRoleRepository, SysRoleService sysRoleService) {
        this.sysDefaultRoleRepository = sysDefaultRoleRepository;
        this.sysRoleService = sysRoleService;
    }

    @Override
    public BaseRepository<SysDefaultRole, String> getRepository() {
        return this.sysDefaultRoleRepository;
    }

    public SysDefaultRole findByScene(AccountType scene) {
        return this.sysDefaultRoleRepository.findByScene(scene);
    }


    public SysDefaultRole assign(String defaultId, String roleId) {
        SysRole sysRole = sysRoleService.findByRoleId(roleId);
        SysDefaultRole sysDefaultRole = sysDefaultRoleRepository.findByDefaultId(defaultId);
        if (ObjectUtils.isNotEmpty(sysDefaultRole) && ObjectUtils.isNotEmpty(sysRole)) {
            sysDefaultRole.setRole(sysRole);
            return sysDefaultRoleRepository.saveAndFlush(sysDefaultRole);
        }

        return null;
    }
}
