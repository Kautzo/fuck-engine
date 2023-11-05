package cn.fuck.engine.upms.service;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.data.core.service.BaseManager;
import cn.fuck.engine.rest.core.service.BaseService;
import cn.fuck.engine.upms.dto.SysUserSaveDTO;
import cn.fuck.engine.upms.dto.SysUserUpdateDTO;
import cn.fuck.engine.upms.entity.SysUser;
import cn.fuck.engine.upms.manager.SysUserManager;
import cn.fuck.engine.upms.vo.SysUserResultVO;

import java.util.List;

public interface SysUserService
        extends BaseService<SysUser, SysUserSaveDTO, SysUserUpdateDTO, SysUser, SysUserResultVO> {

    void updateUserRole(String userId, List<String> roleIds);

    void changePassword(String userId, String password);

    SysUserResultVO getByUserName(String userName);

    void changeStatus(String userId, DataItemStatus status);
}
