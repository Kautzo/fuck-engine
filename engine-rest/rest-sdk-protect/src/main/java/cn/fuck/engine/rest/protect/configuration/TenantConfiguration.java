package cn.fuck.engine.rest.protect.configuration;

import cn.fuck.engine.rest.protect.tenant.MultiTenantInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 租户请求拦截配置 </p>
 */
@Slf4j
@AutoConfiguration
public class TenantConfiguration {
    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Protect Tenant] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public MultiTenantInterceptor tenantInterceptor() {
        MultiTenantInterceptor multiTenantInterceptor = new MultiTenantInterceptor();
        log.trace("[FUCK] |- Bean [Idempotent Interceptor] Auto Configure.");
        return multiTenantInterceptor;
    }
}
