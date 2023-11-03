package cn.fuck.engine.rest.protect.crypto.enhance;

import cn.fuck.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import cn.fuck.engine.assistant.core.utils.http.SessionUtils;
import cn.fuck.engine.rest.protect.crypto.processor.HttpCryptoProcessor;
import cn.fuck.engine.rest.core.annotation.Crypto;
import cn.fuck.engine.rest.core.exception.SessionInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>Description: 响应体加密Advice </p>
 * @date : 2021/10/4 14:30
 */
@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(EncryptResponseBodyAdvice.class);

    private HttpCryptoProcessor httpCryptoProcessor;

    public void setInterfaceCryptoProcessor(HttpCryptoProcessor httpCryptoProcessor) {
        this.httpCryptoProcessor = httpCryptoProcessor;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        String methodName = methodParameter.getMethod().getName();
        Crypto crypto = methodParameter.getMethodAnnotation(Crypto.class);

        boolean isSupports = ObjectUtils.isNotEmpty(crypto) && crypto.responseEncrypt();

        log.trace("[FUCK] |- Is EncryptResponseBodyAdvice supports method [{}] ? Status is [{}].", methodName, isSupports);
        return isSupports;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        String sessionId = SessionUtils.analyseSessionId(request);

        if (StringUtils.isBlank(sessionId)) {
            log.warn("[FUCK] |- Cannot find FUCK Cloud custom session header. Use interface crypto function need add X_FUCK_SESSION_ID to request header.");
            return body;
        }

        log.info("[FUCK] |- EncryptResponseBodyAdvice begin encrypt data.");

        String methodName = methodParameter.getMethod().getName();
        String className = methodParameter.getDeclaringClass().getName();

        try {
            String bodyString = Jackson2Utils.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(body);
            String result = httpCryptoProcessor.encrypt(sessionId, bodyString);
            if (StringUtils.isNotBlank(result)) {
                log.debug("[FUCK] |- Encrypt response body for rest method [{}] in [{}] finished.", methodName, className);
                return result;
            } else {
                return body;
            }
        } catch (JsonProcessingException e) {
            log.debug("[FUCK] |- Encrypt response body for rest method [{}] in [{}] catch error, skip encrypt operation.", methodName, className, e);
            return body;
        } catch (SessionInvalidException e) {
            log.error("[FUCK] |- Session is expired for encrypt response body for rest method [{}] in [{}], skip encrypt operation.", methodName, className, e);
            return body;
        }
    }
}
