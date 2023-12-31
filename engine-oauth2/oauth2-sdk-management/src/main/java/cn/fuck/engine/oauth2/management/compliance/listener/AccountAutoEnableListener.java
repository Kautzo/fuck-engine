package cn.fuck.engine.oauth2.management.compliance.listener;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import cn.fuck.engine.oauth2.management.compliance.OAuth2AccountStatusManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: 账户锁定状态监听 </p>
 */
@Slf4j
public class AccountAutoEnableListener extends KeyExpirationEventMessageListener {

    private final OAuth2AccountStatusManager accountStatusManager;

    public AccountAutoEnableListener(RedisMessageListenerContainer listenerContainer,
                                     OAuth2AccountStatusManager accountStatusManager) {
        super(listenerContainer);
        this.accountStatusManager = accountStatusManager;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        if (StringUtils.contains(key, OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL)) {
            String userId = StringUtils.substringAfterLast(key, SymbolConstants.COLON);
            log.info("[FUCK] |- Parse the user [{}] at expired redis cache key [{}]", userId, key);
            if (StringUtils.isNotBlank(userId)) {
                log.debug("[FUCK] |- Automatically unlock user account [{}]", userId);
                accountStatusManager.enable(userId);
            }
        }
    }
}
