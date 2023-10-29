package cn.fuck.engine.supplier.message.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Description: 消息互动 </p>
 * @date : 2022/12/6 21:29
 */
@Configuration(proxyBeanMethods = false)
@EntityScan(basePackages = {
        "cn.fuck.engine.supplier.message.entity"
})
@EnableJpaRepositories(basePackages = {
        "cn.fuck.engine.supplier.message.repository",
})
@ComponentScan(basePackages = {
        "cn.fuck.engine.supplier.message.service",
        "cn.fuck.engine.supplier.message.controller",
        "cn.fuck.engine.supplier.message.listener",
})
public class SupplierMessageConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SupplierMessageConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Supplier Message] Auto Configure.");
    }
}
