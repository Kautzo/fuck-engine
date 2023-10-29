package cn.fuck.engine.access.wxmpp.configuration;

import cn.fuck.engine.access.wxmpp.annotation.ConditionalOnWxmppEnabled;
import cn.fuck.engine.access.wxmpp.processor.WxmppLogHandler;
import cn.fuck.engine.access.wxmpp.processor.WxmppProcessor;
import cn.fuck.engine.access.wxmpp.properties.WxmppProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>Description: 微信公众号配置 </p>
 * @date : 2021/4/7 13:25
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWxmppEnabled
@EnableConfigurationProperties(WxmppProperties.class)
public class WxmppConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WxmppConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[FUCK] |- SDK [Access Wxmpp] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxmppProcessor wxmppProcessor(WxmppProperties wxmppProperties, StringRedisTemplate stringRedisTemplate) {
        WxmppProcessor wxmppProcessor = new WxmppProcessor();
        wxmppProcessor.setWxmppProperties(wxmppProperties);
        wxmppProcessor.setWxmppLogHandler(new WxmppLogHandler());
        wxmppProcessor.setStringRedisTemplate(stringRedisTemplate);
        log.trace("[FUCK] |- Bean [Wxmpp Processor] Auto Configure.");
        return wxmppProcessor;
    }
}
