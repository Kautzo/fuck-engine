package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.converter.SysUserToFuckUserConverter;
import cn.fuck.engine.upms.entity.SysUser;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.mapper.SysUserMapper;
import cn.fuck.engine.upms.service.SysUserRoleService;
import cn.fuck.engine.upms.service.SysUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.dromara.hutool.core.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: SysUserService </p>
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    private final Converter<SysUser, FuckUser> toUser = new SysUserToFuckUserConverter();

    @Override
    public SysUser getByUserName(String userName) {
        return getOne(Wrappers.lambdaQuery(SysUser.class)
                .eq(SysUser::getUserName, userName)
                .last("LIMIT 1"));
    }

    @Override
    @Transactional
    public Boolean handlerDelete(List<String> ids) {
        removeByIds(ids);
        sysUserRoleService.remove(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getUserId, ids));
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(String userId, String password) {
        SysUser sysUser = getById(userId);
        Assert.notNull(sysUser, "userId error");
        sysUser.setPassword(SecurityUtils.encrypt(password));
        updateById(sysUser);
    }

    @Override
    @Transactional
    public void updateUserRole(String userId, List<String> roleIds) {
        SysUser sysUser = getById(userId);
        Assert.notNull(sysUser, "userId error");

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
