package cn.fuck.engine.rest.protect.tenant;

import cn.fuck.engine.assistant.core.utils.http.SessionUtils;
import cn.fuck.engine.assistant.core.context.TenantContextHolder;
import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.assistant.core.utils.http.HeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>Description: 多租户拦截器 </p>
 * @date : 2022/9/6 11:16
 */
public class MultiTenantInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(MultiTenantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenantId = HeaderUtils.getHerodotusTenantId(request);
        if (StringUtils.isBlank(tenantId)) {
            tenantId = DefaultConstants.TENANT_ID;
        }
        TenantContextHolder.setTenantId(tenantId);
        log.debug("[FUCK] |- TENANT ID is : [{}].", tenantId);

        String path = request.getRequestURI();
        String sessionId = SessionUtils.getSessionId(request);
        String herodotusSessionId = HeaderUtils.getHerodotusSession(request);

        log.debug("[FUCK] |- SESSION ID for [{}] is : [{}].", path, sessionId);
        log.debug("[FUCK] |- SESSION ID of HERODOTUS for [{}] is : [{}].", path, herodotusSessionId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String path = request.getRequestURI();
        TenantContextHolder.clear();
        log.debug("[FUCK] |- Tenant Interceptor CLEAR tenantId for request [{}].", path);
    }
}
