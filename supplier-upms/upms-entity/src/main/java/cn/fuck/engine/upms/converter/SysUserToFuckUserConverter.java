package cn.fuck.engine.upms.converter;

import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.oauth2.core.definition.domain.FuckGrantedAuthority;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.entity.SysRole;
import cn.fuck.engine.upms.entity.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: SysUser 转 FuckUser 转换器 </p>
 */
public class SysUserToFuckUserConverter implements Converter<SysUser, FuckUser> {
    @Override
    public FuckUser convert(SysUser sysUser) {

        Set<FuckGrantedAuthority> authorities = new HashSet<>();

        return new FuckUser(sysUser.getId(), sysUser.getUserName(), sysUser.getPassword(),
                isEnabled(sysUser),
                isAccountNonExpired(sysUser),
                isCredentialsNonExpired(sysUser),
                isNonLocked(sysUser),
                authorities);
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
