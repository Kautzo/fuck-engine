package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.mapper.SysUserRoleMapper;
import cn.fuck.engine.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public List<SysUserRole> getByUserId(String userId) {
        return list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userId));
    }
}
