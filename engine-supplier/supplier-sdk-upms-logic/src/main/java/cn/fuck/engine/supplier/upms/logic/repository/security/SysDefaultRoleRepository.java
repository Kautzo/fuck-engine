package cn.fuck.engine.supplier.upms.logic.repository.security;

import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysDefaultRole;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

/**
 * <p>Description: 默认角色管理Repository </p>
 * @date : 2021/7/21 16:08
 */
public interface SysDefaultRoleRepository extends BaseRepository<SysDefaultRole, String> {

    /**
     * 根据场景查询当前场景对应的默认角色
     *
     * @param scene 场景 {@link AccountType}
     * @return {@link SysDefaultRole}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysDefaultRole findByScene(AccountType scene);

    /**
     * 根据默认角色ID查询默认角色
     *
     * @param defaultId 默认角色ID
     * @return {@link SysDefaultRole}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysDefaultRole findByDefaultId(String defaultId);
}
