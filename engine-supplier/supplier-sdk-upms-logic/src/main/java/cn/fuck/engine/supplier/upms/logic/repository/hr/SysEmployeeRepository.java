package cn.fuck.engine.supplier.upms.logic.repository.hr;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysEmployee;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.QueryHints;

/**
 * <p>Description: 人员 Repository </p>
 * @date : 2020/1/20 11:47
 */
public interface SysEmployeeRepository extends BaseRepository<SysEmployee, String> {

    /**
     * 根据人员性名查找SysEmployee
     *
     * @param employeeName 人员姓名
     * @return {@link SysEmployee}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @EntityGraph(value = "SysEmployeeWithSysUser.Graph", type = EntityGraph.EntityGraphType.FETCH)
    SysEmployee findByEmployeeName(String employeeName);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    @EntityGraph(value = "SysEmployeeWithSysUser.Graph", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    Page<SysEmployee> findAll(Pageable pageable);


}
