package cn.fuck.engine.access.all.configuration;

import cn.fuck.engine.access.all.controller.JustAuthAccessController;
import cn.fuck.engine.access.all.controller.WxappAccessController;
import cn.fuck.engine.access.all.processor.AccessHandlerStrategyFactory;
import cn.fuck.engine.access.all.processor.PhoneNumberAccessHandler;
import cn.fuck.engine.access.justauth.annotation.ConditionalOnJustAuthEnabled;
import cn.fuck.engine.access.justauth.configuration.JustAuthConfiguration;
import cn.fuck.engine.access.wxapp.annotation.ConditionalOnWxappEnabled;
import cn.fuck.engine.access.wxapp.configuration.WxappConfiguration;
import cn.fuck.engine.access.wxmpp.configuration.WxmppConfiguration;
import cn.fuck.engine.assistant.core.enums.AccountType;
import cn.fuck.engine.access.all.controller.PhoneNumberAccessController;
import cn.fuck.engine.sms.autoconfigure.SmsAutoConfiguration;
import cn.fuck.engine.sms.autoconfigure.annotation.ConditionalOnSmsEnabled;
import cn.fuck.engine.sms.autoconfigure.processor.SmsSendStrategyFactory;
import cn.fuck.engine.sms.autoconfigure.stamp.VerificationCodeStampManager;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>Description: Access 业务模块配置 </p>
 * @date : 2022/1/26 14:59
 */
@Configuration(proxyBeanMethods = false)
@Import({
        JustAuthConfiguration.class,
        WxappConfiguration.class,
        WxmppConfiguration.class
})
public class AccessAllConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AccessAllConfiguration.class);

    @PostConstruct
    public void init() {
        log.debug("[FUCK] |- SDK [Access All] Auto Configure.");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnSmsEnabled
    @Import({SmsAutoConfiguration.class})
    static class PhoneNumberSignInConfiguration {

        @Bean(AccountType.PHONE_NUMBER_HANDLER)
        @ConditionalOnBean({VerificationCodeStampManager.class, SmsSendStrategyFactory.class})
        public PhoneNumberAccessHandler phoneNumberAccessHandler(VerificationCodeStampManager verificationCodeStampManager, SmsSendStrategyFactory smsSendStrategyFactory) {
            PhoneNumberAccessHandler phoneNumberAuthenticationHandler = new PhoneNumberAccessHandler(verificationCodeStampManager, smsSendStrategyFactory);
            log.trace("[FUCK] |- Bean [Phone Number SignIn Handler] Auto Configure.");
            return phoneNumberAuthenticationHandler;
        }
    }

    @Bean
    @ConditionalOnMissingBean(AccessHandlerStrategyFactory.class)
    public AccessHandlerStrategyFactory accessHandlerStrategyFactory() {
        AccessHandlerStrategyFactory accessHandlerStrategyFactory = new AccessHandlerStrategyFactory();
        log.trace("[FUCK] |- Bean [Access Handler Strategy Factory] Auto Configure.");
        return accessHandlerStrategyFactory;
    }

    @Configuration(proxyBeanMethods = false)
    static class ControllerConfiguration {

        @PostConstruct
        public void init() {
            log.debug("[FUCK] |- SDK [Access All Controller] Auto Configure.");
        }

        @Bean
        @ConditionalOnSmsEnabled
        @ConditionalOnMissingBean
        public PhoneNumberAccessController phoneNumberAccessController() {
            PhoneNumberAccessController phoneNumberAuthenticationController = new PhoneNumberAccessController();
            log.trace("[FUCK] |- Bean [Phone Number Access Controller] Auto Configure.");
            return phoneNumberAuthenticationController;
        }

        @Bean
        @ConditionalOnJustAuthEnabled
        @ConditionalOnMissingBean
        public JustAuthAccessController justAuthSignInController() {
            JustAuthAccessController justAuthAuthenticationController = new JustAuthAccessController();
            log.trace("[FUCK] |- Bean [Just Auth Access Controller] Auto Configure.");
            return justAuthAuthenticationController;
        }

        @Bean
        @ConditionalOnWxappEnabled
        @ConditionalOnMissingBean
        public WxappAccessController wxappAccessController() {
            WxappAccessController wxappAccessController = new WxappAccessController();
            log.trace("[FUCK] |- Bean [Wxapp Access Controller] Auto Configure.");
            return wxappAccessController;
        }
    }
}
