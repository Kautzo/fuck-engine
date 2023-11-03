package cn.fuck.engine.sms.qiniu.configuration;

import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.qiniu.annotation.ConditionalOnQiniuSmsEnabled;
import cn.fuck.engine.sms.qiniu.processor.QiniuSmsSendHandler;
import cn.fuck.engine.sms.qiniu.properties.QiniuSmsProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 七牛云短信发送配置类 </p>
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnQiniuSmsEnabled
@EnableConfigurationProperties(QiniuSmsProperties.class)
public class QiniuSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(QiniuSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms Qiniu] Auto Configure.");
    }

    /**
     * 构造七牛云发送处理
     *
     * @param qiniuSmsProperties 配置对象
     * @return 七牛云发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_QINIU)
    public QiniuSmsSendHandler qiniuSmsSendHandler(QiniuSmsProperties qiniuSmsProperties) {
        QiniuSmsSendHandler qiniuSmsSendHandler = new QiniuSmsSendHandler(qiniuSmsProperties);
        log.debug("[FUCK] |- Bean [Qiniu Sms Send Handler] Auto Configure.");
        return qiniuSmsSendHandler;
    }
}
