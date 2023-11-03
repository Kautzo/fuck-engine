package cn.fuck.engine.sms.chinamobile.processor;

import cn.fuck.engine.sms.chinamobile.properties.ChinaMobileSmsProperties;
import cn.fuck.engine.sms.chinamobile.domain.ChinaMobileSmsRequest;
import cn.fuck.engine.sms.core.definition.AbstractSmsSendHandler;
import cn.fuck.engine.sms.core.domain.Template;
import cn.fuck.engine.sms.core.enums.SmsSupplier;
import cn.fuck.engine.sms.core.exception.ParameterOrdersInvalidException;
import cn.fuck.engine.sms.core.exception.TemplateIdInvalidException;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p>Description: 移动云发送处理 </p>
 * @date : 2021/5/25 14:57
 */
public class ChinaMobileSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(ChinaMobileSmsSendHandler.class);

    private final ChinaMobileSmsProperties properties;

    public ChinaMobileSmsSendHandler(ChinaMobileSmsProperties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.CHINA_MOBILE.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {
        String templateId = this.getTemplateId(template);
        String templateParams = this.getOrderedParamsString(template);

        ChinaMobileSmsRequest request = new ChinaMobileSmsRequest(
                this.properties.getEcName(),
                this.properties.getApId(),
                this.properties.getSecretKey(),
                templateId,
                join(phones),
                templateParams,
                this.properties.getSign());

        HttpResult result = this.http().sync(this.properties.getUri())
                .bodyType(OkHttps.FORM)
                .setBodyPara(request)
                .nothrow()
                .post();

        return result.isSuccessful();
    }
}
