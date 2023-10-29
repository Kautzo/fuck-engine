package cn.fuck.engine.access.wxapp.configuration;

import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.access.wxapp.annotation.ConditionalOnWxappEnabled;
import cn.fuck.engine.access.wxapp.processor.WxappAccessHandler;
import cn.fuck.engine.access.wxapp.processor.WxappLogHandler;
import cn.fuck.engine.access.wxapp.processor.WxappProcessor;
import cn.fuck.engine.access.wxapp.properties.WxappProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 微信小程序后配置 </p>
 * @date : 2021/3/29 9:27
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxappEnabled
@EnableConfigurationProperties(WxappProperties.class)
public class WxappConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WxappConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[FUCK] |- SDK [Access Wxapp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxappProcessor wxappProcessor(WxappProperties wxappProperties) {
        WxappProcessor wxappProcessor = new WxappProcessor();
        wxappProcessor.setWxappProperties(wxappProperties);
        wxappProcessor.setWxappLogHandler(new WxappLogHandler());
        log.trace("[FUCK] |- Bean [Wxapp Processor] Auto Configure.");
        return wxappProcessor;
    }

    @Bean(AccountType.WECHAT_MINI_APP_HANDLER)
    @ConditionalOnBean(WxappProcessor.class)
    @ConditionalOnMissingBean
    public WxappAccessHandler wxappAccessHandler(WxappProcessor wxappProcessor) {
        WxappAccessHandler wxappAccessHandler = new WxappAccessHandler(wxappProcessor);
        log.debug("[FUCK] |- Bean [Wxapp Access Handler] Auto Configure.");
        return wxappAccessHandler;
    }
}
