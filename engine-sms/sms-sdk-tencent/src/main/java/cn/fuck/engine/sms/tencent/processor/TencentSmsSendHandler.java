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
package cn.fuck.engine.sms.tencent.processor;

import cn.fuck.engine.sms.tencent.properties.TencentSmsProperties;
import cn.fuck.engine.assistant.core.utils.type.ListUtils;
import cn.fuck.engine.sms.core.definition.AbstractSmsSendHandler;
import cn.fuck.engine.sms.core.domain.Template;
import cn.fuck.engine.sms.core.enums.SmsSupplier;
import cn.fuck.engine.sms.core.exception.ParameterOrdersInvalidException;
import cn.fuck.engine.sms.core.exception.TemplateIdInvalidException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.apache.commons.collections4.CollectionUtils;
import org.dromara.hutool.core.array.ArrayUtil;
import org.dromara.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: 腾讯云短信发送处理器 </p>
 * @date : 2021/5/25 15:59
 */
public class TencentSmsSendHandler extends AbstractSmsSendHandler {

    private static final String SUCCESS_CODE = "OK";

    private final SmsClient sender;
    private final TencentSmsProperties properties;

    public TencentSmsSendHandler(TencentSmsProperties properties) {
        super(properties);
        this.properties = properties;

        Credential credential = new Credential(this.properties.getSecretId(), this.properties.getSecretKey());
        sender = new SmsClient(credential, this.properties.getRegion());
    }

    @Override
    protected String getChannel() {
        return SmsSupplier.TENCENT_CLOUD.name();
    }

    @Override
    protected boolean execute(Template template, List<String> phones) throws TemplateIdInvalidException, ParameterOrdersInvalidException {
        List<List<String>> groups = CollUtil.partition(phones, 200);
        String templateId = this.getTemplateId(template);
        List<String> templateParams = this.getOrderedParams(template);
        List<List<String>> errors = groups.parallelStream().map(group -> this.send(templateId, group, templateParams)).collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(errors)) {
            for (List<String> subErrors : errors) {
                result.addAll(subErrors);
            }
        }

        return CollectionUtils.isEmpty(result);
    }

    private List<String> send(String templateId, List<String> mobileGroup, List<String> templateParams) {

        try {
            SendSmsRequest request = new SendSmsRequest();
            request.setSmsSdkAppid(this.properties.getSmsAppId());
            request.setSign(this.properties.getSmsSign());
            request.setTemplateID(templateId);
            request.setTemplateParamSet(ListUtils.toStringArray(templateParams));
            request.setPhoneNumberSet(ListUtils.toStringArray(mobileGroup));

            SendSmsResponse sendSmsResponse = sender.SendSms(request);
            if (ArrayUtil.isEmpty(sendSmsResponse.getSendStatusSet())) {
                return mobileGroup;
            } else {
                SendStatus[] sendStatuses = sendSmsResponse.getSendStatusSet();
                return Arrays.stream(sendStatuses)
                        .filter(sendStatus -> !sendStatus.getCode().equals(SUCCESS_CODE))
                        .map(SendStatus::getPhoneNumber).collect(Collectors.toList());
            }
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}