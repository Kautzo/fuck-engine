package cn.fuck.engine.supplier.upms.logic.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description: UPMS SDK 模块配置 </p>
 * @date : 2022/2/16 19:00
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
        "cn.fuck.engine.supplier.upms.logic.entity.security",
        "cn.fuck.engine.supplier.upms.logic.entity.hr",
})
@EnableJpaRepositories(basePackages = {
        "cn.fuck.engine.supplier.upms.logic.repository.security",
        "cn.fuck.engine.supplier.upms.logic.repository.hr",
})
@ComponentScan(basePackages = {
        "cn.fuck.engine.supplier.upms.logic.service.security",
        "cn.fuck.engine.supplier.upms.logic.service.hr",
        "cn.fuck.engine.supplier.upms.logic.service.assistant",
})
public class SupplierUpmsLogicConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SupplierUpmsLogicConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Supplier Upms Logic] Auto Configure.");
    }
}
