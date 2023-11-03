package cn.fuck.engine.rest.protect.secure.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>Description: Xss 过滤器 </p>
 */
@Slf4j
@Component
public class XssHttpServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        log.trace("[FUCK] |- XssHttpServletFilter wrapper request for [{}].", request.getRequestURI());
        filterChain.doFilter(xssRequest, servletResponse);
    }
}
