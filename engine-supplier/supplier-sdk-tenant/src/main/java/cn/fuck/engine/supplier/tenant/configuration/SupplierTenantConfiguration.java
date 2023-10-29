package cn.fuck.engine.supplier.tenant.configuration;

import cn.fuck.engine.data.tenant.annotation.ConditionalOnDatabaseApproach;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Description: 多租户 </p>
 * @date : 2023/3/29 21:18
 */
@AutoConfiguration
@ConditionalOnDatabaseApproach
@ComponentScan(basePackages = {
        "cn.fuck.engine.supplier.tenant.service",
        "cn.fuck.engine.supplier.tenant.controller",
})
public class SupplierTenantConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SupplierTenantConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Supplier Tenant] Auto Configure.");
    }
}
