package cn.fuck.engine.sms.qiniu.properties;

import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.core.definition.AbstractSmsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Description: 七牛云短信配置 </p>
 * @date : 2021/5/26 14:26
 */
@ConfigurationProperties(prefix = SmsConstants.PROPERTY_PREFIX_QINIU)
public class QiniuSmsProperties extends AbstractSmsProperties {

    /**
     * accessKey
     */
    private String accessKey;

    /**
     * secretKey
     */
    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
