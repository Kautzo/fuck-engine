package cn.fuck.engine.sms.upyun.configuration;

import cn.fuck.engine.sms.upyun.processor.UpyunSmsSendHandler;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.upyun.annotation.ConditionalOnUpyunSmsEnabled;
import cn.fuck.engine.sms.upyun.properties.UpyunSmsProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 又拍短信发送配置类 </p>
 * @date : 2021/5/25 15:25
 */

@Configuration(proxyBeanMethods = false)
@ConditionalOnUpyunSmsEnabled
@EnableConfigurationProperties(UpyunSmsProperties.class)
public class UpyunSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(UpyunSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms Upyun] Auto Configure.");
    }

    /**
     * 构造又拍云发送处理
     *
     * @param upyunSmsProperties 配置对象
     * @return 又拍云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_UPYUN)
    public UpyunSmsSendHandler upyunSmsSendHandler(UpyunSmsProperties upyunSmsProperties) {
        UpyunSmsSendHandler upyunSmsSendHandler = new UpyunSmsSendHandler(upyunSmsProperties);
        log.debug("[FUCK] |- Bean [Upyun Sms Send Handler] Auto Configure.");
        return upyunSmsSendHandler;
    }
}
