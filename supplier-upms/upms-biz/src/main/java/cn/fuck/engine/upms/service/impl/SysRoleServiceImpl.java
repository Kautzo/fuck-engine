package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseManagerImpl;
import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysRole;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.entity.SysUserRole;
import cn.fuck.engine.upms.manager.SysRoleManager;
import cn.fuck.engine.upms.mapper.SysRoleMapper;
import cn.fuck.engine.upms.service.SysRolePermissionService;
import cn.fuck.engine.upms.service.SysRoleService;
import cn.fuck.engine.upms.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>Description: SysRoleService </p>
 */
@Slf4j
@Service
public class SysRoleServiceImpl
        extends BaseServiceImpl<SysRoleManager, SysRole, SysRole, SysRole, SysRole, SysRole>
        implements SysRoleService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    @Transactional
    public void updateRolePermission(String roleId, List<String> permissionIds) {
        List<SysRolePermission> dbPermissionList = sysRolePermissionService.getByRoleId(roleId);

        List<SysRolePermission> saveList = new ArrayList<>();
        List<SysRolePermission> removeList = new ArrayList<>();

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<SysRolePermission> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(permissionIds, item.getPermissionId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(SysRolePermission::getPermissionId).toList();
            List<String> save = permissionIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String permissionId : save) {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setRoleId(roleId);
                    sysRolePermission.setPermissionId(permissionId);
                    saveList.add(sysRolePermission);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(permissionIds)) {
                for (String permissionId : permissionIds) {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setRoleId(roleId);
                    sysRolePermission.setPermissionId(permissionId);
                    saveList.add(sysRolePermission);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            sysRolePermissionService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(SysRolePermission::getId).toList();
            sysRolePermissionService.removeByIds(removeIds);
        }
    }

    @Override
    public SysRole getByRoleCode(String roleCode) {
        return baseManger.getOne(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getRoleCode, roleCode).last("LIMIT 1"));
    }

    @Override
    public boolean removeByIds(Collection<String> idList) {
        super.removeByIds(idList);
        sysRolePermissionService.removeByRoleIdS(idList);
        sysUserRoleService.removeByRoleIds(idList);
        return Boolean.TRUE;
    }

}
