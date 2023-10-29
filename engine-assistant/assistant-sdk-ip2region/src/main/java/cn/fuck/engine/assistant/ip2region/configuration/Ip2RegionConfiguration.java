package cn.fuck.engine.assistant.ip2region.configuration;

import cn.fuck.engine.assistant.ip2region.definition.Ip2RegionSearcher;
import cn.fuck.engine.assistant.ip2region.searcher.DefaultIp2RegionSearcher;
import cn.fuck.engine.assistant.ip2region.properties.Ip2RegionProperties;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * <p>Description: Ip2Region 配置 </p>
 * @date : 2023/10/24 11:59
 */
@AutoConfiguration
@EnableConfigurationProperties(Ip2RegionProperties.class)
public class Ip2RegionConfiguration {

    private static final Logger log = LoggerFactory.getLogger(Ip2RegionConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Ip2Region] Auto Configure.");
    }

    @Bean
    public Ip2RegionSearcher defaultIp2RegionSearcher(Ip2RegionProperties ip2RegionProperties) {
        DefaultIp2RegionSearcher searcher = new DefaultIp2RegionSearcher(ip2RegionProperties.getIpV4Resource(), ip2RegionProperties.getIpV6Resource());
        log.trace("[FUCK] |- Bean [Ip2Region Searcher] Auto Configure.");
        return searcher;
    }

}
