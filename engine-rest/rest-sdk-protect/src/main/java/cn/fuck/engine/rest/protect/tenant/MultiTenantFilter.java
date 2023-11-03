package cn.fuck.engine.rest.protect.tenant;

import cn.fuck.engine.assistant.core.context.TenantContextHolder;
import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.assistant.core.utils.http.HeaderUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * <p>Description: 多租户过滤器 </p>
 */
public class MultiTenantFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String tenantId = HeaderUtils.getFuckTenantId(request);
        TenantContextHolder.setTenantId(StringUtils.isBlank(tenantId) ? DefaultConstants.TENANT_ID : tenantId);

        filterChain.doFilter(servletRequest, servletResponse);
        TenantContextHolder.clear();
    }

    @Override
    public void destroy() {
        TenantContextHolder.clear();
    }
}
