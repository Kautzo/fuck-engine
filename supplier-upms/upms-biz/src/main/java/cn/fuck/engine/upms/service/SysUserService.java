package cn.fuck.engine.upms.service;

import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.upms.entity.SysUser;

import java.util.List;

public interface SysUserService extends BaseService<SysUser> {

    void updateUserRole(String userId, List<String> roleIds);

    void changePassword(String userId, String password);

    SysUser getByUserName(String userName);

    Boolean handlerDelete(List<String> ids);
}
