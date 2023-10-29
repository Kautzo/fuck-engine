package cn.fuck.engine.supplier.upms.logic.repository.hr;

import cn.fuck.engine.assistant.core.exception.transaction.TransactionalRollbackException;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysOwnership;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: 人事归属Repository </p>
 * @date : 2021/7/15 16:28
 */
public interface SysOwnershipRepository extends BaseRepository<SysOwnership, String> {

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param organizationId 单位ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.organizationId = :organizationId")
    void deleteByOrganizationId(String organizationId);

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param departmentId 部门ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.departmentId = :departmentId")
    void deleteByDepartmentId(String departmentId);

    /**
     * 根据单位ID删除人事归属
     * <p>
     * 从操作的完整性上应该包含该操作，但是这个操作风险很大，会删除较多内容
     *
     * @param employeeId 人员ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.employeeId = :employeeId")
    void deleteByEmployeeId(String employeeId);

    /**
     * 删除人事归属
     *
     * @param organizationId 单位ID
     * @param departmentId   部门ID
     * @param employeeId     人员ID
     */
    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query(value = "delete from SysOwnership o where o.organizationId = :organizationId and o.departmentId = :departmentId and o.employeeId = :employeeId")
    void deleteByOrganizationIdAndDepartmentIdAndEmployeeId(String organizationId, String departmentId, String employeeId);

}
