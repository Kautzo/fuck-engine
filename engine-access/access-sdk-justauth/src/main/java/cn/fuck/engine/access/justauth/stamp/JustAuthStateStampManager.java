package cn.fuck.engine.access.justauth.stamp;

import cn.fuck.engine.access.justauth.properties.JustAuthProperties;
import cn.fuck.engine.access.core.constants.AccessConstants;
import cn.fuck.engine.cache.jetcache.stamp.AbstractStampManager;
import me.zhyd.oauth.cache.AuthStateCache;
import org.dromara.hutool.core.data.id.IdUtil;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 自定义JustAuth State Cache </p>
 * @date : 2021/5/22 10:22
 */
public class JustAuthStateStampManager extends AbstractStampManager<String, String> implements AuthStateCache {

    public JustAuthStateStampManager() {
        super(AccessConstants.CACHE_NAME_TOKEN_JUSTAUTH);
    }

    private JustAuthProperties justAuthProperties;

    public void setJustAuthProperties(JustAuthProperties justAuthProperties) {
        this.justAuthProperties = justAuthProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(justAuthProperties.getTimeout());
    }

    @Override
    public String nextStamp(String key) {
        return IdUtil.fastSimpleUUID();
    }

    @Override
    public void cache(String key, String value) {
        this.put(key, value);
    }

    @Override
    public void cache(String key, String value, long expire) {
        this.put(key, value, expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean containsKey(String key) {
        return this.containKey(key);
    }
}
