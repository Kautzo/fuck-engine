package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.supplier.upms.logic.repository.security.SysAttributeRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysAttribute;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysPermission;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Description: SysAttributeService </p>
 * @date : 2021/8/4 6:48
 */
@Service
public class SysAttributeService extends BaseService<SysAttribute, String> {

    private final SysAttributeRepository sysAttributeRepository;

    public SysAttributeService(SysAttributeRepository sysAttributeRepository) {
        this.sysAttributeRepository = sysAttributeRepository;
    }

    @Override
    public BaseRepository<SysAttribute, String> getRepository() {
        return this.sysAttributeRepository;
    }

    public SysAttribute assign(String attributeId, String[] permissionIds) {

        Set<SysPermission> sysPermissions = new HashSet<>();
        for (String permissionId : permissionIds) {
            SysPermission sysPermission = new SysPermission();
            sysPermission.setPermissionId(permissionId);
            sysPermissions.add(sysPermission);
        }

        SysAttribute sysAttribute = findById(attributeId);
        sysAttribute.setPermissions(sysPermissions);

        return saveAndFlush(sysAttribute);
    }

    public List<SysAttribute> findAllByServiceId(String serviceId) {
        return sysAttributeRepository.findAllByServiceId(serviceId);
    }

    public List<SysAttribute> findByAttributeIdIn(List<String> ids) {
        return sysAttributeRepository.findByAttributeIdIn(ids);
    }
}
