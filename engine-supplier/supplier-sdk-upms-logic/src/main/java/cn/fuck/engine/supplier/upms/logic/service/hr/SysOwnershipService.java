package cn.fuck.engine.supplier.upms.logic.service.hr;

import cn.fuck.engine.supplier.upms.logic.repository.hr.SysOwnershipRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysOwnership;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 人事归属服务 </p>
 * @date : 2021/7/15 16:30
 */
@Service
public class SysOwnershipService extends BaseService<SysOwnership, String> {

    private static final Logger log = LoggerFactory.getLogger(SysOwnershipService.class);

    private final SysOwnershipRepository sysOwnershipRepository;

    public SysOwnershipService(SysOwnershipRepository sysOwnershipRepository) {
        this.sysOwnershipRepository = sysOwnershipRepository;
    }

    @Override
    public BaseRepository<SysOwnership, String> getRepository() {
        return this.sysOwnershipRepository;
    }

    public void deleteByOrganizationId(String organizationId) {
        sysOwnershipRepository.deleteByOrganizationId(organizationId);
    }

    public void deleteByDepartmentId(String departmentId) {
        sysOwnershipRepository.deleteByDepartmentId(departmentId);
    }

    public void deleteByEmployeeId(String employeeId) {
        sysOwnershipRepository.deleteByEmployeeId(employeeId);
    }

    public void delete(String organizationId, String departmentId, String employeeId) {
        sysOwnershipRepository.deleteByOrganizationIdAndDepartmentIdAndEmployeeId(organizationId, departmentId, employeeId);
    }
}
