package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.manager.SysPermissionManager;
import cn.fuck.engine.upms.mapper.SysPermissionMapper;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import cn.fuck.engine.upms.service.SysPermissionService;
import cn.fuck.engine.upms.service.SysRolePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>Description: SysPermissionService </p>
 */
@Service
public class SysPermissionServiceImpl
        extends BaseServiceImpl<SysPermissionManager, SysPermission, SysPermission, SysPermission, SysPermission, SysPermission>
        implements SysPermissionService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysAttributePermissionService sysAttributePermissionService;

    @Override
    @Transactional
    public boolean removeByIds(Collection<String> idList) {
        super.removeByIds(idList);
        sysRolePermissionService.removeByPermissionIdS(idList);
        sysAttributePermissionService.removeByPermissionIds(idList);
        return Boolean.TRUE;
    }

}
