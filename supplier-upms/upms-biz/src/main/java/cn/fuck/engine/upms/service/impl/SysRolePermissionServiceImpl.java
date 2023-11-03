package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.mapper.SysRolePermissionMapper;
import cn.fuck.engine.upms.service.SysRolePermissionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Override
    public List<SysRolePermission> getByRoleId(String roleId) {
        return list(Wrappers.lambdaQuery(SysRolePermission.class).eq(SysRolePermission::getRoleId, roleId));
    }
}
