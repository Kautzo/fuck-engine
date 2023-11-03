package cn.fuck.engine.sms.huawei.configuration;

import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.huawei.annotation.ConditionalOnHuaweiSmsEnabled;
import cn.fuck.engine.sms.huawei.processor.HuaweiSmsSendHandler;
import cn.fuck.engine.sms.huawei.properties.HuaweiSmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 华为云短信发送配置类 </p>
 * @date : 2021/5/25 14:48
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnHuaweiSmsEnabled
@EnableConfigurationProperties(HuaweiSmsProperties.class)
public class HuaweiSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HuaweiSmsConfiguration.class);

    /**
     * 构造华为云发送处理
     *
     * @param huaweiSmsProperties 配置对象
     * @return 华为云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_HUAWEI_CLOUD)
    public HuaweiSmsSendHandler huaweiCloudSmsSendHandler(HuaweiSmsProperties huaweiSmsProperties) {
        HuaweiSmsSendHandler huaweiSmsSendHandler = new HuaweiSmsSendHandler(huaweiSmsProperties);
        log.debug("[FUCK] |- Bean [Huawei Sms Send Handler] Auto Configure.");
        return huaweiSmsSendHandler;
    }
}
