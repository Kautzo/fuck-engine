package cn.fuck.engine.facility.tencent.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * <p>Description: 基础设置自动配置 </p>
 * @date : 2022/2/5 19:09
 */
@AutoConfiguration
public class FacilityTencentAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FacilityTencentAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Facility Tencent Starter] Auto Configure.");
    }
}
