package cn.fuck.engine.sms.tencent.configuration;

import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.tencent.annotation.ConditionalOnTencentSmsEnabled;
import cn.fuck.engine.sms.tencent.processor.TencentSmsSendHandler;
import cn.fuck.engine.sms.tencent.properties.TencentSmsProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 腾讯云短信发送配置类 </p>
 * @date : 2021/5/25 15:24
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnTencentSmsEnabled
@EnableConfigurationProperties(TencentSmsProperties.class)
public class TencentSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TencentSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms Tencent] Auto Configure.");
    }

    /**
     * 构造腾讯云V3发送处理
     *
     * @param tencentSmsProperties 配置对象
     * @return 腾讯云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_TENCENT_CLOUD)
    public TencentSmsSendHandler tencentCloudSmsSendHandler(TencentSmsProperties tencentSmsProperties) {
        TencentSmsSendHandler tencentSmsSendHandler = new TencentSmsSendHandler(tencentSmsProperties);
        log.debug("[FUCK] |- Bean [Tencent Sms Send Handler] Auto Configure.");
        return tencentSmsSendHandler;
    }
}
