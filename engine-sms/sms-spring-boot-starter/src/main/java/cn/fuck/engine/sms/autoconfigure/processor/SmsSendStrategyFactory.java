package cn.fuck.engine.sms.autoconfigure.processor;

import cn.fuck.engine.sms.autoconfigure.properties.SmsProperties;
import cn.fuck.engine.sms.core.definition.SmsSendHandler;
import cn.fuck.engine.sms.core.domain.Template;
import cn.fuck.engine.sms.core.enums.SmsSupplier;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Description: 短信发送工厂 </p>
 */
@Component
public class SmsSendStrategyFactory {

    private static final Logger log = LoggerFactory.getLogger(SmsSendStrategyFactory.class);
    @Autowired
    private final Map<String, SmsSendHandler> handlers = new ConcurrentHashMap<>();
    private SmsProperties smsProperties;

    public void setSmsProperties(SmsProperties smsProperties) {
        this.smsProperties = smsProperties;
    }

    public boolean send(Template template, String phone) {
        SmsSupplier smsSupplier = smsProperties.getDefaultChannel();
        if (ObjectUtils.isNotEmpty(smsSupplier)) {
            return this.send(smsSupplier.name(), template, phone);
        } else {
            log.error("[FUCK] |- Default sms channel is not correct!");
            return false;
        }
    }

    public boolean send(Template template, List<String> phones) {
        SmsSupplier smsSupplier = smsProperties.getDefaultChannel();
        if (ObjectUtils.isNotEmpty(smsSupplier)) {
            return this.send(smsSupplier.name(), template, phones);
        } else {
            log.error("[FUCK] |- Default sms channel is not correct!");
            return false;
        }
    }

    public boolean send(String channel, Template template, String phone) {
        SmsSendHandler handler = handlers.get(channel);
        if (ObjectUtils.isNotEmpty(handler)) {
            return handler.send(template, ImmutableList.of(phone));
        } else {
            log.error("[FUCK] |- Sms channel [{}] is not config!", channel);
            return false;
        }
    }

    public boolean send(String channel, Template template, List<String> phones) {
        SmsSendHandler handler = handlers.get(channel);
        if (ObjectUtils.isNotEmpty(handler)) {
            return handler.send(template, phones);
        } else {
            log.error("[FUCK] |- Sms channel [{}] is not config!", channel);
            return false;
        }
    }


}
