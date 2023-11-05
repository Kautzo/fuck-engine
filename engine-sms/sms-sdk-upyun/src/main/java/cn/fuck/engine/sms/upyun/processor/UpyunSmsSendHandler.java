package cn.fuck.engine.sms.upyun.processor;

import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import cn.fuck.engine.sms.core.definition.AbstractSmsSendHandler;
import cn.fuck.engine.sms.core.domain.Template;
import cn.fuck.engine.sms.core.enums.SmsSupplier;
import cn.fuck.engine.sms.core.exception.ParameterOrdersInvalidException;
import cn.fuck.engine.sms.core.exception.TemplateIdInvalidException;
import cn.fuck.engine.sms.upyun.domain.UpyunSmsRequest;
import cn.fuck.engine.sms.upyun.domain.UpyunSmsResponse;
import cn.fuck.engine.sms.upyun.properties.UpyunSmsProperties;
import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.hc.core5.http.HttpHeaders;

import java.util.List;

/**
 * <p>Description: 又拍云短信发送处理器 </p>
 */
public class UpyunSmsSendHandler extends AbstractSmsSendHandler {

    private final UpyunSmsProperties properties;

    public UpyunSmsSendHandler(UpyunSmsProperties properties) {
        super(properties);
        this.properties = properties;
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.UPYUN.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        String templateId = this.getTemplateId(template);
        List<String> orderParams = this.getOrderedParams(template);
        String templateParams = this.join(orderParams, SymbolConstants.PIPE);

        UpyunSmsRequest request = new UpyunSmsRequest();
        request.setMobile(join(phones));
        request.setTemplateId(templateId);
        request.setVars(templateParams);

        HttpResult result = this.http().sync(this.properties.getApiUrl())
                .bodyType(OkHttps.JSON)
                .addHeader(HttpHeaders.AUTHORIZATION, this.properties.getToken())
                .setBodyPara(request)
                .nothrow()
                .post();

        if (result.isSuccessful()) {
            UpyunSmsResponse upyunSmsResponse = result.getBody().toBean(UpyunSmsResponse.class);
            if (ObjectUtils.isNotEmpty(upyunSmsResponse)) {
                return true;
            }
        }

        return false;
    }
}
