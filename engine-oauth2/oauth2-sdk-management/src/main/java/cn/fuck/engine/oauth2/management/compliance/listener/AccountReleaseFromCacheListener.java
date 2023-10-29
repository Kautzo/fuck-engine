package cn.fuck.engine.oauth2.management.compliance.listener;

import cn.fuck.engine.message.core.event.AccountReleaseFromCacheEvent;
import cn.fuck.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import org.springframework.context.ApplicationListener;

/**
 * <p>Description: TODO </p>
 * @date : 2023/5/14 14:33
 */
public class AccountReleaseFromCacheListener implements ApplicationListener<AccountReleaseFromCacheEvent> {

    private final OAuth2AccountStatusManager accountStatusManager;

    public AccountReleaseFromCacheListener(OAuth2AccountStatusManager accountStatusManager) {
        this.accountStatusManager = accountStatusManager;
    }

    @Override
    public void onApplicationEvent(AccountReleaseFromCacheEvent event) {
        String username = event.getData();
        accountStatusManager.releaseFromCache(username);
    }
}
