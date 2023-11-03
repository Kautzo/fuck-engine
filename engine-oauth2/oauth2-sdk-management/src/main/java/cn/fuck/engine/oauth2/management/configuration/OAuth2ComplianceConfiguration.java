package cn.fuck.engine.oauth2.management.configuration;

import cn.fuck.engine.message.core.definition.AccountStatusEventManager;
import cn.fuck.engine.oauth2.authentication.stamp.LockedUserDetailsStampManager;
import cn.fuck.engine.oauth2.authentication.stamp.SignInFailureLimitedStampManager;
import cn.fuck.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import cn.fuck.engine.oauth2.management.compliance.annotation.ConditionalOnAutoUnlockUserAccount;
import cn.fuck.engine.oauth2.management.compliance.listener.AccountAutoEnableListener;
import cn.fuck.engine.oauth2.management.compliance.listener.AuthenticationFailureListener;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>Description: OAuth2 应用安全合规配置 </p>
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AccountStatusEventManager.class)
public class OAuth2ComplianceConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [OAuth2 Compliance] Auto Configure.");
    }

    @Bean
    public OAuth2AccountStatusManager accountStatusManager(UserDetailsService userDetailsService,
                                                           AccountStatusEventManager accountStatusChanger,
                                                           LockedUserDetailsStampManager lockedUserDetailsStampManager) {
        OAuth2AccountStatusManager manager = new OAuth2AccountStatusManager(userDetailsService, accountStatusChanger, lockedUserDetailsStampManager);
        log.trace("[FUCK] |- Bean [OAuth2 Account Status Manager] Auto Configure.");
        return manager;
    }

    @Bean
    @ConditionalOnAutoUnlockUserAccount
    public AccountAutoEnableListener accountLockStatusListener(RedisMessageListenerContainer redisMessageListenerContainer,
                                                               OAuth2AccountStatusManager accountStatusManager) {
        AccountAutoEnableListener listener = new AccountAutoEnableListener(redisMessageListenerContainer, accountStatusManager);
        log.trace("[FUCK] |- Bean [OAuth2 Account Lock Status Listener] Auto Configure.");
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureListener authenticationFailureListener(SignInFailureLimitedStampManager stampManager,
                                                                       OAuth2AccountStatusManager accountLockService) {
        AuthenticationFailureListener listener = new AuthenticationFailureListener(stampManager, accountLockService);
        log.trace("[FUCK] |- Bean [OAuth2 Authentication Failure Listener] Auto Configure.");
        return listener;
    }
}
