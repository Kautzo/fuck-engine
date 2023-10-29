package cn.fuck.engine.supplier.upms.logic.service.security;

import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.supplier.upms.logic.repository.security.SysUserRepository;
import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.definition.domain.SocialUserDetails;
import cn.fuck.engine.oauth2.core.utils.SecurityUtils;
import cn.fuck.engine.supplier.upms.logic.converter.SysUserToHerodotusUserConverter;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysDefaultRole;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysRole;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.core.data.id.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: SysUserService </p>
 * @date : 2019/11/9 10:03
 */
@Service
public class SysUserService extends BaseService<SysUser, String> {

    private static final Logger log = LoggerFactory.getLogger(SysUserService.class);

    private final Converter<SysUser, FuckUser> toUser;

    private final SysUserRepository sysUserRepository;
    private final SysDefaultRoleService sysDefaultRoleService;

    public SysUserService(SysUserRepository sysUserRepository, SysDefaultRoleService sysDefaultRoleService) {
        this.sysUserRepository = sysUserRepository;
        this.sysDefaultRoleService = sysDefaultRoleService;
        this.toUser = new SysUserToHerodotusUserConverter();
    }

    @Override
    public BaseRepository<SysUser, String> getRepository() {
        return sysUserRepository;
    }

    public SysUser findByUserName(String userName) {
        return sysUserRepository.findByUserName(userName);
    }

    public SysUser findByUserId(String userId) {
        return sysUserRepository.findByUserId(userId);
    }

    public SysUser changePassword(String userId, String password) {
        SysUser sysUser = findByUserId(userId);
        sysUser.setPassword(SecurityUtils.encrypt(password));
        return saveAndFlush(sysUser);
    }

    public SysUser assign(String userId, String[] roleIds) {
        SysUser sysUser = findByUserId(userId);
        return this.register(sysUser, roleIds);
    }

    public SysUser register(SysUser sysUser, String[] roleIds) {
        Set<SysRole> sysRoles = new HashSet<>();
        for (String roleId : roleIds) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(roleId);
            sysRoles.add(sysRole);
        }
        return this.register(sysUser, sysRoles);
    }

    public SysUser register(SysUser sysUser, AccountType source) {
        SysDefaultRole sysDefaultRole = sysDefaultRoleService.findByScene(source);
        if (ObjectUtils.isNotEmpty(sysDefaultRole)) {
            SysRole sysRole = sysDefaultRole.getRole();
            if (ObjectUtils.isNotEmpty(sysRole)) {
                return this.register(sysUser, sysRole);
            }
        }
        log.error("[FUCK] |- Default role for [{}] is not set correct, may case register error!", source);
        return null;
    }

    public SysUser register(SysUser sysUser, SysRole sysRole) {
        Set<SysRole> sysRoles = new HashSet<>();
        sysRoles.add(sysRole);
        return this.register(sysUser, sysRoles);
    }

    public SysUser register(SysUser sysUser, Set<SysRole> sysRoles) {
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            sysUser.setRoles(sysRoles);
        }
        return saveAndFlush(sysUser);
    }

    private String enhance(String userName) {
        if (StringUtils.isNotBlank(userName)) {
            SysUser checkedSysUser = this.findByUserName(userName);
            if (ObjectUtils.isNotEmpty(checkedSysUser)) {
                return checkedSysUser.getUserName() + IdUtil.nanoId(6);
            } else {
                return userName;
            }
        } else {
            return "Herodotus" + IdUtil.nanoId(6);
        }
    }

    public SysUser register(SocialUserDetails socialUserDetails) {
        SysUser sysUser = new SysUser();

        String userName = enhance(socialUserDetails.getUserName());
        sysUser.setUserName(userName);

        String nickName = socialUserDetails.getNickName();
        if (StringUtils.isNotBlank(nickName)) {
            sysUser.setNickName(nickName);
        }

        String phoneNumber = socialUserDetails.getPhoneNumber();
        if (StringUtils.isNotBlank(phoneNumber)) {
            sysUser.setPhoneNumber(SecurityUtils.encrypt(phoneNumber));
        }

        String avatar = socialUserDetails.getAvatar();
        if (StringUtils.isNotBlank(avatar)) {
            sysUser.setAvatar(avatar);
        }

        sysUser.setPassword(SecurityUtils.encrypt("herodotus-cloud"));

        return register(sysUser, AccountType.getAccountType(socialUserDetails.getSource()));
    }

    public FuckUser registerUserDetails(SocialUserDetails socialUserDetails) {
        SysUser newSysUser = register(socialUserDetails);
        return toUser.convert(newSysUser);
    }

    public void changeStatus(String userId, DataItemStatus status) {
        SysUser sysUser = findByUserId(userId);
        if (ObjectUtils.isNotEmpty(sysUser)) {
            sysUser.setStatus(status);
            log.debug("[FUCK] |- Change user [{}] status to [{}]", sysUser.getUserName(), status.name());
            save(sysUser);
        }
    }
}
