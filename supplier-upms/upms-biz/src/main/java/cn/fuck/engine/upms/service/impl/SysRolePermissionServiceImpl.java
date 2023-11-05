package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.manager.SysRolePermissionManager;
import cn.fuck.engine.upms.service.SysRolePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class SysRolePermissionServiceImpl
        extends BaseServiceImpl<SysRolePermissionManager, SysRolePermission, SysRolePermission, SysRolePermission, SysRolePermission, SysRolePermission>
        implements SysRolePermissionService {

    @Override
    public List<SysRolePermission> getByRoleId(String roleId) {
        return baseManger.list(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, roleId));
    }

    @Override
    @Transactional
    public void removeByRoleIdS(Collection<String> roleId) {
        baseManger.remove(Wrappers.lambdaQuery(SysRolePermission.class).in(SysRolePermission::getRoleId, roleId));
    }

    @Override
    @Transactional
    public void removeByPermissionIdS(Collection<String> permissionId) {
        baseManger.remove(Wrappers.lambdaQuery(SysRolePermission.class).in(SysRolePermission::getPermissionId, permissionId));
    }
}
