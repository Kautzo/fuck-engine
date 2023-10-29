package cn.fuck.engine.supplier.upms.logic.entity.hr;

import cn.fuck.engine.supplier.upms.logic.constants.UpmsConstants;
import cn.fuck.engine.data.core.entity.BaseSysEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 人事归属 </p>
 * @date : 2021/7/15 12:16
 */
@Schema(title = "人事归属")
@Entity
@Table(name = "sys_ownership", indexes = {
        @Index(name = "sys_ownership_id_idx", columnList = "ownership_id"),
        @Index(name = "sys_ownership_oid_idx", columnList = "organization_id"),
        @Index(name = "sys_ownership_did_idx", columnList = "department_id"),
        @Index(name = "sys_ownership_eid_idx", columnList = "employee_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = UpmsConstants.REGION_SYS_OWNERSHIP)
public class SysOwnership extends BaseSysEntity {

    @Schema(title = "人员ID")
    @Id
    @UuidGenerator
    @Column(name = "ownership_id", length = 64)
    private String ownershipId;

    @Schema(title = "所属单位ID")
    @Column(name = "organization_id", length = 64)
    private String organizationId;

    @Schema(title = "所属部门ID")
    @Column(name = "department_id", length = 64)
    private String departmentId;

    @Column(name = "employee_id", length = 64)
    private String employeeId;

    public String getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(String ownershipId) {
        this.ownershipId = ownershipId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ownershipId", ownershipId)
                .add("organizationId", organizationId)
                .add("departmentId", departmentId)
                .add("employeeId", employeeId)
                .toString();
    }
}
