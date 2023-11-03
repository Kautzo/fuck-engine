package cn.fuck.engine.rest.core.definition;

import cn.fuck.engine.assistant.core.utils.http.SessionUtils;
import cn.fuck.engine.assistant.core.definition.constants.SymbolConstants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dromara.hutool.crypto.SecureUtil;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>Description: 基础拦截器 </p>
 * <p>
 * 定义拦截器通用方法
 */
@Slf4j
public abstract class AbstractBaseHandlerInterceptor implements HandlerInterceptor {

    protected String generateRequestKey(HttpServletRequest request) {

        String sessionId = SessionUtils.analyseSessionId(request);

        String url = request.getRequestURI();
        String method = request.getMethod();

        if (StringUtils.isNotBlank(sessionId)) {
            String key = SecureUtil.md5(sessionId + SymbolConstants.COLON + url + SymbolConstants.COLON + method);
            log.debug("[FUCK] |- IdempotentInterceptor key is [{}].", key);
            return key;
        } else {
            log.warn("[FUCK] |- IdempotentInterceptor cannot create key, because sessionId is null.");
            return null;
        }
    }
}
