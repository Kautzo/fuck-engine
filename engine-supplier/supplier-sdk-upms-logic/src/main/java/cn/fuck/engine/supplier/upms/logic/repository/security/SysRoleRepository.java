package cn.fuck.engine.supplier.upms.logic.repository.security;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

/**
 * <p>Description: SysRoleRepository </p>
 * @date : 2021/10/11 21:27
 */
public interface SysRoleRepository extends BaseRepository<SysRole, String> {

    /**
     * 根据用户名查找SysUser
     *
     * @param roleCode 角色代码
     * @return {@link SysRole}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysRole findByRoleCode(String roleCode);

    /**
     * 根据角色ID查询角色
     *
     * @param roleId 角色ID
     * @return {@link SysRole}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysRole findByRoleId(String roleId);
}
