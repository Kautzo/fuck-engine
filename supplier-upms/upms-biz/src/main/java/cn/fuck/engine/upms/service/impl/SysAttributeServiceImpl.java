package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.data.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.entity.SysAttribute;
import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.entity.SysRolePermission;
import cn.fuck.engine.upms.mapper.SysAttributeMapper;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import cn.fuck.engine.upms.service.SysAttributeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: SysAttributeService </p>
 */
@Slf4j
@Service
public class SysAttributeServiceImpl extends BaseServiceImpl<SysAttributeMapper, SysAttribute> implements SysAttributeService {

    @Autowired
    private SysAttributePermissionService sysAttributePermissionService;

    @Override
    @Transactional
    public void updateAttributePermission(String attributeId, List<String> permissionIds) {
        List<SysAttributePermission> dbPermissionList = sysAttributePermissionService.getByAttributeId(attributeId);

        List<SysAttributePermission> saveList = new ArrayList<>();
        List<SysAttributePermission> removeList = new ArrayList<>();

        if (CollUtil.isNotEmpty(dbPermissionList)) {
            List<SysAttributePermission> remove = dbPermissionList.parallelStream()
                    .filter(item -> !CollUtil.contains(permissionIds, item.getPermissionId()))
                    .toList();
            removeList.addAll(remove);

            List<String> dbIds = dbPermissionList.stream().map(SysAttributePermission::getPermissionId).toList();
            List<String> save = permissionIds.parallelStream()
                    .filter(item -> !CollUtil.contains(dbIds, item))
                    .toList();
            if (CollUtil.isNotEmpty(save)) {
                for (String permissionId : save) {
                    SysAttributePermission sysAttributePermission = new SysAttributePermission();
                    sysAttributePermission.setAttributeId(attributeId);
                    sysAttributePermission.setPermissionId(permissionId);
                    saveList.add(sysAttributePermission);
                }
            }
        } else {
            if (CollUtil.isNotEmpty(permissionIds)) {
                for (String permissionId : permissionIds) {
                    SysAttributePermission sysAttributePermission = new SysAttributePermission();
                    sysAttributePermission.setAttributeId(attributeId);
                    sysAttributePermission.setPermissionId(permissionId);
                    saveList.add(sysAttributePermission);
                }
            }
        }

        if (CollUtil.isNotEmpty(saveList)) {
            sysAttributePermissionService.saveBatch(saveList);
        }
        if (CollUtil.isNotEmpty(removeList)) {
            List<String> removeIds = removeList.stream().map(SysAttributePermission::getId).toList();
            sysAttributePermissionService.removeByIds(removeIds);
        }
    }

    public List<SysAttribute> getByServiceId(String serviceId) {
        return list(Wrappers.lambdaQuery(SysAttribute.class)
                .eq(SysAttribute::getServiceId, serviceId));
    }

    @Override
    @Transactional
    public Boolean handlerDelete(List<String> ids) {
        removeByIds(ids);
        sysAttributePermissionService.remove(Wrappers.lambdaQuery(SysAttributePermission.class)
                .in(SysAttributePermission::getAttributeId, ids));
        return Boolean.TRUE;
    }

}
