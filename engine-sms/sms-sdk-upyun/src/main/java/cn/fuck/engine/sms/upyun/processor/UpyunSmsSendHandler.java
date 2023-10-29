/*
 * Copyright (c) 2020-2030 郑庚伟 ZHENGGENGWEI (码匠君) (herodotus@aliyun.com & www.herodotus.cn)
 *
 * Dante Engine licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <http://www.gnu.org/licenses/lgpl.html>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @date : 2021/5/25 15:59
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