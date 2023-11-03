package cn.fuck.engine.sms.netease.properties;

import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.core.definition.AbstractSmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 网易云信短信配置 </p>
 * @date : 2021/5/26 14:37
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_NETEASE)
public class NeteaseSmsProperties extends AbstractSmsProperties {

    private String apiUrl = "https://api.netease.im/sms/sendtemplate.action";
    /**
     * appkey
     */
    private String appKey;

    /**
     * appSecret
     */
    private String appSecret;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
