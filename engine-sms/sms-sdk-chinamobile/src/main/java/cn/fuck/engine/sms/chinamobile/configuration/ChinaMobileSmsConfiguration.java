package cn.fuck.engine.sms.chinamobile.configuration;

import cn.fuck.engine.sms.chinamobile.annotation.ConditionalOnChinaMobileSmsEnabled;
import cn.fuck.engine.sms.chinamobile.processor.ChinaMobileSmsSendHandler;
import cn.fuck.engine.sms.chinamobile.properties.ChinaMobileSmsProperties;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 移动云短信发送配置 </p>
 * @date : 2021/5/26 16:13
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnChinaMobileSmsEnabled
@EnableConfigurationProperties(ChinaMobileSmsProperties.class)
public class ChinaMobileSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ChinaMobileSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms China Mobile] Auto Configure.");
    }

    /**
     * 构造移动云发送处理
     *
     * @param chinaMobileSmsProperties 配置对象
     * @return 移动云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_CHINA_MOBILE)
    public ChinaMobileSmsSendHandler chinaMobileSmsSendHandler(ChinaMobileSmsProperties chinaMobileSmsProperties) {
        ChinaMobileSmsSendHandler chinaMobileSmsSendHandler = new ChinaMobileSmsSendHandler(chinaMobileSmsProperties);
        log.debug("[FUCK] |- Bean [China Mobile Sms Send Handler] Auto Configure.");
        return chinaMobileSmsSendHandler;
    }
}
