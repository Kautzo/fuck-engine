package cn.fuck.engine.sms.aliyun.configuration;

import cn.fuck.engine.sms.aliyun.annotation.ConditionalOnAliyunSmsEnabled;
import cn.fuck.engine.sms.aliyun.processor.AliyunSmsSendHandler;
import cn.fuck.engine.sms.aliyun.properties.AliyunSmsProperties;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 阿里云短信发送配置类 </p>
 * @date : 2021/5/25 12:08
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnAliyunSmsEnabled
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class AliyunSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AliyunSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms Aliyun] Auto Configure.");
    }

    /**
     * 构造阿里云发送处理
     *
     * @param aliyunSmsProperties 配置对象
     * @return 阿里云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_ALIYUN)
    public AliyunSmsSendHandler aliyunSmsSendHandler(AliyunSmsProperties aliyunSmsProperties) {
        AliyunSmsSendHandler aliyunSmsSendHandler = new AliyunSmsSendHandler(aliyunSmsProperties);
        log.debug("[FUCK] |- Bean [Aliyun Sms Send Handler] Auto Configure.");
        return aliyunSmsSendHandler;
    }
}
