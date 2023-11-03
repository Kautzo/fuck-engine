package cn.fuck.engine.upms.service;

import cn.fuck.engine.upms.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {
    List<SysUserRole> getByUserId(String userId);
}
