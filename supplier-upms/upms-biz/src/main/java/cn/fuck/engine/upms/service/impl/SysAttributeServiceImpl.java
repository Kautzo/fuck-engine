package cn.fuck.engine.upms.service.impl;

import cn.fuck.engine.rest.core.service.impl.BaseServiceImpl;
import cn.fuck.engine.upms.domain.listener.SysAttributeEntityListener;
import cn.fuck.engine.upms.entity.SysAttribute;
import cn.fuck.engine.upms.entity.SysAttributePermission;
import cn.fuck.engine.upms.manager.SysAttributeManager;
import cn.fuck.engine.upms.service.SysAttributePermissionService;
import cn.fuck.engine.upms.service.SysAttributeService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Description: SysAttributeService </p>
 */
@Slf4j
@Service
public class SysAttributeServiceImpl
        extends BaseServiceImpl<SysAttributeManager, SysAttribute, SysAttribute, SysAttribute, SysAttribute, SysAttribute>
        implements SysAttributeService {

    @Autowired
    private SysAttributePermissionService sysAttributePermissionService;
    @Autowired
    private SysAttributeEntityListener sysAttributeEntityListener;


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
        return baseManger.list(Wrappers.lambdaQuery(SysAttribute.class)
                .eq(SysAttribute::getServiceId, serviceId));
    }

    @Override
    protected void updateAfter(SysAttribute sysAttribute, SysAttribute entity) {
        sysAttributeEntityListener.postUpdate(sysAttribute);
    }

    @Override
    @Transactional
    public boolean removeByIds(Collection<String> idList) {
        super.removeByIds(idList);
        sysAttributePermissionService.removeByAttributeIds(idList);
        return Boolean.TRUE;
    }

}
