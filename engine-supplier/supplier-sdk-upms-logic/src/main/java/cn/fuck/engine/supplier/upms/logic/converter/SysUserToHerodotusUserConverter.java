package cn.fuck.engine.supplier.upms.logic.converter;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.oauth2.core.definition.domain.HerodotusGrantedAuthority;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysPermission;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: SysUser 转 HerodotusUser 转换器 </p>
 * @date : 2023/8/23 23:08
 */
public class SysUserToHerodotusUserConverter implements Converter<SysUser, FuckUser> {
    @Override
    public FuckUser convert(SysUser sysUser) {

        Set<HerodotusGrantedAuthority> authorities = new HashSet<>();

        Set<String> roles = new HashSet<>();
        for (SysRole sysRole : sysUser.getRoles()) {
            roles.add(sysRole.getRoleCode());
            authorities.add(new HerodotusGrantedAuthority(SecurityUtils.wellFormRolePrefix(sysRole.getRoleCode())));
            Set<SysPermission> sysPermissions = sysRole.getPermissions();
            if (CollectionUtils.isNotEmpty(sysPermissions)) {
                sysPermissions.forEach(sysAuthority -> authorities.add(new HerodotusGrantedAuthority((sysAuthority.getPermissionCode()))));
            }
        }

        String employeeId = ObjectUtils.isNotEmpty(sysUser.getEmployee()) ? sysUser.getEmployee().getEmployeeId() : null;

        return new FuckUser(sysUser.getUserId(), sysUser.getUserName(), sysUser.getPassword(),
                isEnabled(sysUser),
                isAccountNonExpired(sysUser),
                isCredentialsNonExpired(sysUser),
                isNonLocked(sysUser),
                authorities, roles, employeeId, sysUser.getAvatar());
    }

    private boolean isEnabled(SysUser sysUser) {
        return sysUser.getStatus() != DataItemStatus.FORBIDDEN;
    }

    private boolean isNonLocked(SysUser sysUser) {
        return !(sysUser.getStatus() == DataItemStatus.LOCKING);
    }

    private boolean isNonExpired(LocalDateTime localDateTime) {
        if (ObjectUtils.isEmpty(localDateTime)) {
            return true;
        } else {
            return localDateTime.isAfter(LocalDateTime.now());
        }
    }

    private boolean isAccountNonExpired(SysUser sysUser) {
        if (sysUser.getStatus() == DataItemStatus.EXPIRED) {
            return false;
        }

        return isNonExpired(sysUser.getAccountExpireAt());
    }

    private boolean isCredentialsNonExpired(SysUser sysUser) {
        return isNonExpired(sysUser.getCredentialsExpireAt());
    }
}
