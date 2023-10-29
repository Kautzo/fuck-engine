package cn.fuck.engine.oauth2.core.response;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.core.exception.SecurityGlobalExceptionHandler;
import cn.fuck.engine.rest.core.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * <p>Description: 自定义未认证处理 </p>
 * @date : 2022/3/8 8:55
 */
public class HerodotusAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<String> result = SecurityGlobalExceptionHandler.resolveSecurityException(authException, request.getRequestURI());
        response.setStatus(result.getStatus());
        WebUtils.renderJson(response, result);
    }
}
