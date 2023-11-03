package cn.fuck.engine.facility.alibaba.autoconfigure.sentinel;

import cn.fuck.engine.assistant.core.json.jackson2.utils.Jackson2Utils;
import cn.fuck.engine.facility.alibaba.autoconfigure.sentinel.enhance.FuckSentinelFeign;
import cn.fuck.engine.assistant.core.domain.Result;
import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import feign.Feign;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * <p>Description: 基础设施 Sentinel 配置 </p>
 * @date : 2022/2/5 17:57
 */
@AutoConfiguration(before = SentinelFeignAutoConfiguration.class)
@ConditionalOnClass({SphU.class, Feign.class})
public class FacilityAlibabaSentinelAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(FacilityAlibabaSentinelAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- SDK [Facility Alibaba Sentinel] Auto Configure.");
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() {
        return FuckSentinelFeign.builder();
    }

    /**
     * 限流、熔断统一处理类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(HttpServletRequest.class)
    public static class WebmvcHandler {
        @Bean
        public BlockExceptionHandler webmvcBlockExceptionHandler() {
            return (request, response, e) -> {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                Result<String> result = Result.failure("Too many request, please retry later.");
                response.getWriter().print(Jackson2Utils.toJson(result));
            };
        }

    }

    /**
     * 限流、熔断统一处理类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ServerResponse.class)
    public static class WebfluxHandler {
        @Bean
        public BlockRequestHandler webfluxBlockExceptionHandler() {
            return (exchange, t) ->
                    ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(Result.failure(t.getMessage())));
        }
    }
}
