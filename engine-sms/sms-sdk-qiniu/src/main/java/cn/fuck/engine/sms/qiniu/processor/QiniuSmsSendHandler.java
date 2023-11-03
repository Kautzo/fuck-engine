package cn.fuck.engine.sms.qiniu.processor;

import cn.fuck.engine.sms.qiniu.properties.QiniuSmsProperties;
import cn.fuck.engine.sms.core.definition.AbstractSmsSendHandler;
import cn.fuck.engine.sms.core.domain.Template;
import cn.fuck.engine.sms.core.enums.SmsSupplier;
import cn.fuck.engine.sms.core.exception.ParameterOrdersInvalidException;
import cn.fuck.engine.sms.core.exception.TemplateIdInvalidException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: 七牛云短信发送处理器 </p>
 * @date : 2021/5/25 16:00
 */
public class QiniuSmsSendHandler extends AbstractSmsSendHandler {

    private static final Logger log = LoggerFactory.getLogger(QiniuSmsSendHandler.class);

    private final SmsManager smsManager;
    private final QiniuSmsProperties properties;

    public QiniuSmsSendHandler(QiniuSmsProperties properties) {
        super(properties);
        this.properties = properties;

        Auth auth = Auth.create(this.properties.getAccessKey(), this.properties.getSecretKey());
        smsManager = new SmsManager(auth);
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.QINIU.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {

        String[] mobiles = convertPhonesToArray(phones);
        String templateId = this.getTemplateId(template);
        Map<String, String> templateParams = template.getParams();

        try {
            Response response = smsManager.sendMessage(templateId, mobiles, templateParams);

            return response.isOK();

        } catch (Exception e) {
            log.debug(e.getLocalizedMessage(), e);
        }

        return false;
    }
}
