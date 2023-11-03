package cn.fuck.engine.upms.service;

import cn.fuck.engine.upms.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysRolePermissionService extends IService<SysRolePermission> {

    List<SysRolePermission> getByRoleId(String roleId);

}
