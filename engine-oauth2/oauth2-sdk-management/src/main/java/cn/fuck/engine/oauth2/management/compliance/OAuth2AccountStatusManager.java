package cn.fuck.engine.oauth2.management.compliance;

import cn.fuck.engine.message.core.definition.AccountStatusEventManager;
import cn.fuck.engine.message.core.domain.UserStatus;
import cn.fuck.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.fuck.engine.data.core.enums.DataItemStatus;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.definition.service.EnhanceUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: 账户锁定处理服务 </p>
 */
@Slf4j
@Service
public class OAuth2AccountStatusManager {

    private final UserDetailsService userDetailsService;
    private final AccountStatusEventManager accountStatusEventManager;
    private final LockedUserDetailsStampManager lockedUserDetailsStampManager;

    public OAuth2AccountStatusManager(UserDetailsService userDetailsService, AccountStatusEventManager accountStatusEventManager, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        this.userDetailsService = userDetailsService;
        this.lockedUserDetailsStampManager = lockedUserDetailsStampManager;
        this.accountStatusEventManager = accountStatusEventManager;
    }

    private EnhanceUserDetailsService getUserDetailsService() {
        return (EnhanceUserDetailsService) userDetailsService;
    }

    private String getUserId(String username) {
        EnhanceUserDetailsService enhanceUserDetailsService = getUserDetailsService();
        FuckUser user = enhanceUserDetailsService.loadFuckUserByUsername(username);
        if (ObjectUtils.isNotEmpty(user)) {
            return user.getUserId();
        }

        log.warn("[FUCK] |- Can not found the userid for [{}]", username);
        return null;
    }

    public void lock(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.LOCKING.name()));
            lockedUserDetailsStampManager.put(userId, username);
            log.info("[FUCK] |- User count [{}] has been locked, and record into cache!", username);
        }
    }

    public void enable(String userId) {
        if (ObjectUtils.isNotEmpty(userId)) {
            accountStatusEventManager.postProcess(new UserStatus(userId, DataItemStatus.ENABLE.name()));
        }
    }

    public void releaseFromCache(String username) {
        String userId = getUserId(username);
        if (ObjectUtils.isNotEmpty(userId)) {
            String value = lockedUserDetailsStampManager.get(userId);
            if (StringUtils.isNotEmpty(value)) {
                this.lockedUserDetailsStampManager.delete(userId);
                log.info("[FUCK] |- User count [{}] locked info has been release!", username);
            }
        }
    }
}
