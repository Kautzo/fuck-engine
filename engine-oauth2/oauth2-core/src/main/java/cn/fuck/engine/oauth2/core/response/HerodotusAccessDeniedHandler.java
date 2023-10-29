package cn.fuck.engine.oauth2.core.response;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.core.exception.SecurityGlobalExceptionHandler;
import cn.fuck.engine.rest.core.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * <p>Description: 访问拒绝处理器 </p>
 * @date : 2022/3/8 8:52
 */
public class HerodotusAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveException(accessDeniedException, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
