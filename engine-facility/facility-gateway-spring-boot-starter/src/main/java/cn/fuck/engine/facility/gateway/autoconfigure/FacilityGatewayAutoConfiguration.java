package cn.fuck.engine.facility.gateway.autoconfigure;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.List;

/**
 * <p>Description: 基础设置自动配置 </p>
 * @date : 2022/2/5 19:09
 */
@AutoConfiguration
public class FacilityGatewayAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FacilityGatewayAutoConfiguration.class);

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public FacilityGatewayAutoConfiguration(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Facility Gateway Starter] Auto Configure.");
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        // Register the block exception handler for Spring Cloud Gateway.
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    @Bean
    @ConditionalOnMissingBean(SentinelGatewayFilter.class)
    public SentinelGatewayFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }
}
