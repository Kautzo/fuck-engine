package cn.fuck.engine.rest.protect.configuration;

import cn.fuck.engine.rest.protect.tenant.MultiTenantInterceptor;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: 租户请求拦截配置 </p>
 * @date : 2022/9/6 11:48
 */
@AutoConfiguration
public class TenantConfiguration {

    private static final Logger log = LoggerFactory.getLogger(TenantConfiguration.class);

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
