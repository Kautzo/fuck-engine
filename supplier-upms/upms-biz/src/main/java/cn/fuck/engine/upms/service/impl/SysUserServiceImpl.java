package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.converter.SysUserToFuckUserConverter;
import cn.fuck.engine.upms.dto.SysUserSaveDTO;
import cn.fuck.engine.upms.dto.SysUserUpdateDTO;
import cn.fuck.engine.upms.entity.SysUser;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.manager.SysUserManager;
import cn.fuck.engine.upms.service.SysUserRoleService;
import cn.fuck.engine.upms.service.SysUserService;
import cn.fuck.engine.upms.vo.SysUserResultVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.bean.BeanUtil;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Description: SysUserService </p>
 */
@Slf4j
@Service
public class SysUserServiceImpl
        extends BaseServiceImpl<SysUserManager, SysUser, SysUserSaveDTO, SysUserUpdateDTO, SysUser, SysUserResultVO>
        implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private final Converter<SysUser, FuckUser> toUser = new SysUserToFuckUserConverter();

    @Override
    public SysUserResultVO getByUserName(String userName) {
        SysUser user = baseManger.getOne(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUserName, userName)
                .last("LIMIT 1"));
        return BeanUtil.toBean(user, SysUserResultVO.class);
    }

    @Override
    @Transactional
    public boolean removeByIds(Collection<String> idList) {
        super.removeByIds(idList);
        sysUserRoleService.removeByUserIds(idList);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public void changeStatus(String userId, DataItemStatus status) {
        SysUser user = getById(userId);
        user.setStatus(status);
        baseManger.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String userId, String password) {
        SysUser sysUser = getById(userId);
        sysUser.setPassword(SecurityUtils.encrypt(password));
        baseManger.updateById(sysUser);
    }

    @Override
    @Transactional
    public void updateUserRole(String userId, List<String> roleIds) {
        getById(userId);

        List<SysUserRole> dbPermissionList = sysUserRoleService.getByUserId(userId);

        List<SysUserRole> saveList = new ArrayList<>();
        List<SysUserRole> removeList = new ArrayList<>();

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<SysUserRole> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(roleIds, item.getRoleId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(SysUserRole::getRoleId).toList();
            List<String> save = roleIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String id : save) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(id);
                    saveList.add(sysUserRole);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(roleIds)) {
                for (String id : roleIds) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(id);
                    saveList.add(sysUserRole);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            sysUserRoleService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(SysUserRole::getId).toList();
            sysUserRoleService.removeByIds(removeIds);
        }
    }

}
