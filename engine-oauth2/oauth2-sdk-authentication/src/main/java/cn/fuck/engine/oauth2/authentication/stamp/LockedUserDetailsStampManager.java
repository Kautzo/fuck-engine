package cn.fuck.engine.oauth2.authentication.stamp;

import cn.fuck.engine.oauth2.authentication.properties.OAuth2AuthenticationProperties;
import cn.fuck.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.fuck.engine.oauth2.core.constants.OAuth2Constants;
import org.dromara.hutool.core.data.id.IdUtil;

/**
 * <p>Description: 锁定账户签章管理 </p>
 * @date : 2022/7/8 21:27
 */
public class LockedUserDetailsStampManager extends AbstractStampManager<String, String> {

    private final OAuth2AuthenticationProperties authenticationProperties;

    public LockedUserDetailsStampManager(OAuth2AuthenticationProperties authenticationProperties) {
        super(OAuth2Constants.CACHE_NAME_TOKEN_LOCKED_USER_DETAIL);
        this.authenticationProperties = authenticationProperties;
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(authenticationProperties.getSignInFailureLimited().getExpire());
    }
}
