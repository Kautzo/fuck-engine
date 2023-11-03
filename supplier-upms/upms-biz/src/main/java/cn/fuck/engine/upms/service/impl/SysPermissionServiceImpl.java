package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.mapper.SysPermissionMapper;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import cn.fuck.engine.upms.service.SysPermissionService;
import cn.fuck.engine.upms.service.SysRolePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Description: SysPermissionService </p>
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysAttributePermissionService sysAttributePermissionService;

    @Override
    @Transactional
    public Boolean handlerDelete(List<String> ids) {
        removeByIds(ids);
        sysRolePermissionService.remove(Wrappers.lambdaQuery(SysRolePermission.class)
                .in(SysRolePermission::getPermissionId, ids));
        sysAttributePermissionService.remove(Wrappers.lambdaQuery(SysAttributePermission.class)
                .in(SysAttributePermission::getPermissionId, ids));
        return true;
    }
}
