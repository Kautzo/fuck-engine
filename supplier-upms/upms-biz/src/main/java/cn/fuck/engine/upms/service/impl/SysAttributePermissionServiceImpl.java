package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.manager.SysAttributePermissionManager;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class SysAttributePermissionServiceImpl
        extends BaseServiceImpl<SysAttributePermissionManager, SysAttributePermission, SysAttributePermission, SysAttributePermission, SysAttributePermission, SysAttributePermission>
        implements SysAttributePermissionService {

    @Override
    public List<SysAttributePermission> getByAttributeId(String attributeId) {
        return baseManger.list(Wrappers.lambdaQuery(SysAttributePermission.class)
                .eq(SysAttributePermission::getAttributeId, attributeId));
    }

    @Override
    @Transactional
    public void removeByAttributeIds(Collection<String> attributeIds) {
        baseManger.remove(Wrappers.lambdaQuery(SysAttributePermission.class)
                .in(SysAttributePermission::getAttributeId, attributeIds));
    }

    @Override
    @Transactional
    public void removeByPermissionIds(Collection<String> permissionIds) {
        baseManger.remove(Wrappers.lambdaQuery(SysAttributePermission.class)
                .in(SysAttributePermission::getPermissionId, permissionIds));
    }

}
