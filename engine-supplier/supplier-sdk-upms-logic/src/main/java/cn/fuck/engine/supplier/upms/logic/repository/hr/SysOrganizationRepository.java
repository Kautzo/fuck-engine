package cn.fuck.engine.supplier.upms.logic.repository.hr;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.hr.SysOrganization;
import cn.fuck.engine.supplier.upms.logic.enums.OrganizationCategory;

import java.util.List;

/**
 * <p>Description: 单位管理Repository </p>
 * @date : 2020/1/20 11:37
 */
public interface SysOrganizationRepository extends BaseRepository<SysOrganization, String> {

    /**
     * 根据组织分类查询组织
     *
     * @param category 组织分类 {@link OrganizationCategory}
     * @return 组织列表
     */
    List<SysOrganization> findByCategory(OrganizationCategory category);

}
