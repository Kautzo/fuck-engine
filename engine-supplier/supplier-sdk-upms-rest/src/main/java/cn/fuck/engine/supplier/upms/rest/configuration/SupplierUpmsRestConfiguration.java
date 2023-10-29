package cn.fuck.engine.supplier.upms.rest.configuration;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * <p>Description: UpmsRest配置类 </p>
 * @date : 2021/1/5 11:58
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackages = {
        "cn.fuck.engine.supplier.upms.rest.controller.hr",
        "cn.fuck.engine.supplier.upms.rest.controller.security",
        "cn.fuck.engine.supplier.upms.rest.controller.assistant",
        "cn.fuck.engine.supplier.upms.rest.controller.social",
})
public class SupplierUpmsRestConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SupplierUpmsRestConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- SDK [Supplier Upms Rest] Auto Configure.");
    }


}