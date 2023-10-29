package cn.fuck.engine.sms.autoconfigure;

import cn.fuck.engine.sms.autoconfigure.annotation.ConditionalOnSmsEnabled;
import cn.fuck.engine.sms.autoconfigure.processor.SmsSendStrategyFactory;
import cn.fuck.engine.sms.autoconfigure.stamp.VerificationCodeStampManager;
import cn.fuck.engine.sms.aliyun.configuration.AliyunSmsConfiguration;
import cn.fuck.engine.sms.autoconfigure.properties.SmsProperties;
import cn.fuck.engine.sms.chinamobile.configuration.ChinaMobileSmsConfiguration;
import cn.fuck.engine.sms.core.definition.SmsSendHandler;
import cn.fuck.engine.sms.huawei.configuration.HuaweiSmsConfiguration;
import cn.fuck.engine.sms.netease.configuration.NeteaseSmsConfiguration;
import cn.fuck.engine.sms.qiniu.configuration.QiniuSmsConfiguration;
import cn.fuck.engine.sms.tencent.configuration.TencentSmsConfiguration;
import cn.fuck.engine.sms.upyun.configuration.UpyunSmsConfiguration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: 发送短信统一配置 </p>
 * @date : 2021/5/25 12:03
 */
@AutoConfiguration
@ConditionalOnSmsEnabled
@EnableConfigurationProperties({SmsProperties.class})
@Import({
        AliyunSmsConfiguration.class,
        ChinaMobileSmsConfiguration.class,
        HuaweiSmsConfiguration.class,
        NeteaseSmsConfiguration.class,
        QiniuSmsConfiguration.class,
        TencentSmsConfiguration.class,
        UpyunSmsConfiguration.class,
})
public class SmsAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SmsAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms All] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public VerificationCodeStampManager verificationCodeStampManager(SmsProperties smsProperties) {
        VerificationCodeStampManager verificationCodeStampManager = new VerificationCodeStampManager();
        verificationCodeStampManager.setSmsProperties(smsProperties);
        log.trace("[FUCK] |- Bean [Verification Code Stamp Manager] Auto Configure.");
        return verificationCodeStampManager;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnSingleCandidate(SmsSendHandler.class)
    public SmsSendStrategyFactory smsSendStrategyFactory(SmsProperties smsProperties) {
        SmsSendStrategyFactory smsSendStrategyFactory = new SmsSendStrategyFactory();
        smsSendStrategyFactory.setSmsProperties(smsProperties);
        log.trace("[FUCK] |- Bean [Sms Send Strategy Factory] Auto Configure.");
        return smsSendStrategyFactory;
    }
}
