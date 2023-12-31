package cn.fuck.engine.rest.protect.secure.interceptor;

import cn.fuck.engine.rest.protect.secure.stamp.IdempotentStampManager;
import cn.fuck.engine.rest.core.annotation.Idempotent;
import cn.fuck.engine.rest.core.definition.AbstractBaseHandlerInterceptor;
import cn.fuck.engine.rest.core.exception.RepeatSubmissionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 * <p>Description: 幂等拦截器 </p>
 */
@Slf4j
public class IdempotentInterceptor extends AbstractBaseHandlerInterceptor {

    private static final String IDEMPOTENT_ATTRIBUTE = "Idempotent";

    private IdempotentStampManager idempotentStampManager;

    public void setIdempotentStampManager(IdempotentStampManager idempotentStampManager) {
        this.idempotentStampManager = idempotentStampManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.trace("[FUCK] |- IdempotentInterceptor preHandle postProcess.");

        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();

        Idempotent idempotent = method.getAnnotation(Idempotent.class);
        if (idempotent != null) {
            // 幂等性校验, 根据缓存中是否存在Token进行校验。
            // 如果缓存中没有Token，通过放行, 同时在缓存中存入Token。
            // 如果缓存中有Token，意味着同一个操作反复操作，认为失败则抛出异常, 并通过统一异常处理返回友好提示

            String key = generateRequestKey(request);
            if (StringUtils.isNotBlank(key)) {
                String token = idempotentStampManager.get(key);
                if (StringUtils.isBlank(token)) {
                    Duration configuredDuration = Duration.ZERO;
                    String annotationExpire = idempotent.expire();
                    if (StringUtils.isNotBlank(annotationExpire)) {
                        try {
                            configuredDuration = Duration.parse(annotationExpire);
                        } catch (DateTimeParseException e) {
                            log.warn("[FUCK] |- Idempotent duration value is incorrect, on api [{}].", request.getRequestURI());
                        }
                    }

                    if (!configuredDuration.isZero()) {
                        idempotentStampManager.create(key, configuredDuration);
                    } else {
                        idempotentStampManager.create(key);
                    }

                    request.setAttribute(IDEMPOTENT_ATTRIBUTE, key);

                    return true;
                } else {
                    throw new RepeatSubmissionException("Don't Repeat Submission");
                }
            }
        }

        return true;
    }
}
