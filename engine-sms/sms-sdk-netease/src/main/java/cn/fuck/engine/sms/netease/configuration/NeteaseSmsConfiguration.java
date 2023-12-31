package cn.fuck.engine.sms.netease.configuration;

import cn.fuck.engine.sms.netease.annotation.ConditionalOnNeteaseSmsEnabled;
import cn.fuck.engine.sms.netease.processor.NeteaseSmsSendHandler;
import cn.fuck.engine.sms.core.constants.SmsConstants;
import cn.fuck.engine.sms.netease.properties.NeteaseSmsProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 网易短信发送配置类 </p>
 * @date : 2021/5/25 15:24
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnNeteaseSmsEnabled
@EnableConfigurationProperties(NeteaseSmsProperties.class)
public class NeteaseSmsConfiguration {

    private static final Logger log = LoggerFactory.getLogger(NeteaseSmsConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Sms Netease] Auto Configure.");
    }

    /**
     * 构造网易云信发送处理
     *
     * @param neteaseSmsProperties 配置对象
     * @return 网易云信发送处理
     */
    @Bean(name = SmsConstants.CHANNEL_NETEASE_CLOUD)
    public NeteaseSmsSendHandler neteaseCloudSmsSendHandler(NeteaseSmsProperties neteaseSmsProperties) {
        NeteaseSmsSendHandler neteaseSmsSendHandler = new NeteaseSmsSendHandler(neteaseSmsProperties);
        log.debug("[FUCK] |- Bean [Netease Sms Send Handler] Auto Configure.");
        return neteaseSmsSendHandler;
    }
}
