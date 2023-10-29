package cn.fuck.engine.supplier.upms.logic.repository.hr;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysDepartment;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

/**
 * <p>Description: 部门 Repository</p>
 * @date : 2020/1/20 11:47
 */
public interface SysDepartmentRepository extends BaseRepository<SysDepartment, String> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<SysDepartment> findByOrganizationId(String organizationId);
}
