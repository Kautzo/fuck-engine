package cn.fuck.engine.sms.autoconfigure.stamp;

import cn.fuck.engine.cache.jetcache.stamp.AbstractStampManager;
import cn.fuck.engine.sms.autoconfigure.properties.SmsProperties;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import org.dromara.hutool.core.util.RandomUtil;

/**
 * <p>Description: 手机短信验证码签章 </p>
 */
public class VerificationCodeStampManager extends AbstractStampManager<String, String> {

    private SmsProperties smsProperties;

    public VerificationCodeStampManager() {
        super(SmsConstants.CACHE_NAME_TOKEN_VERIFICATION_CODE);
    }

    public void setSmsProperties(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    @Override
    public String nextStamp(String key) {
        if (smsProperties.getSandbox()) {
            return smsProperties.getTestCode();
        } else {
            return RandomUtil.randomNumbers(smsProperties.getLength());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.setExpire(smsProperties.getExpire());
    }

    public Boolean getSandbox() {
        return smsProperties.getSandbox();
    }

    public String getVerificationCodeTemplateId() {
        return smsProperties.getVerificationCodeTemplateId();
    }
}
