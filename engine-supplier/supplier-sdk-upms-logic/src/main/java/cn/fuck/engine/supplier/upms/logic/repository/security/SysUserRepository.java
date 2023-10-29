package cn.fuck.engine.supplier.upms.logic.repository.security;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysUser;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

/**
 * <p>Description: SysUserRepository </p>
 * @date : 2020/4/8 16:14
 */
public interface SysUserRepository extends BaseRepository<SysUser, String> {
    /**
     * 根据用户名查找SysUser
     *
     * @param userName 用户名
     * @return {@link SysUser}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysUser findByUserName(String userName);

    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户ID
     * @return {@link SysUser}
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    SysUser findByUserId(String userId);
}
