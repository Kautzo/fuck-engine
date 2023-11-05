package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.manager.SysUserRoleManager;
import cn.fuck.engine.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class SysUserRoleServiceImpl
        extends BaseServiceImpl<SysUserRoleManager, SysUserRole, SysUserRole, SysUserRole, SysUserRole, SysUserRole>
        implements SysUserRoleService {

    @Override
    public List<SysUserRole> getByUserId(String userId) {
        return baseManger.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userId));
    }

    @Override
    public void removeByUserIds(Collection<String> userIds) {
        baseManger.remove(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getUserId, userIds));
    }

    @Override
    public void removeByRoleIds(Collection<String> roleIds) {
        baseManger.remove(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getRoleId, roleIds));
    }
}
