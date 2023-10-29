package cn.fuck.engine.rest.autoconfigure;

import cn.fuck.engine.rest.condition.annotation.ConditionalOnUseHttpClient5RestClient;
import cn.fuck.engine.rest.condition.annotation.ConditionalOnUseOkHttp3RestClient;
import cn.fuck.engine.rest.condition.annotation.ConditionalOnUseSimpleRestClient;
import feign.hc5.ApacheHttp5Client;
import jakarta.annotation.PostConstruct;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * <p>Description: Rest Template Configuration </p>
 * @date : 2020/5/29 17:32
 */
@AutoConfiguration(after = {FeignAutoConfiguration.class})
public class RestTemplateAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateAutoConfiguration.class);

    @PostConstruct
    public void postConstruct() {
        log.info("[FUCK] |- Module [Rest Template] Auto Configure.");
    }

    @Bean
    @ConditionalOnClass(okhttp3.OkHttpClient.class)
    @ConditionalOnUseOkHttp3RestClient
    @ConditionalOnMissingBean
    public ClientHttpRequestFactory okHttp3ClientHttpRequestFactory(okhttp3.OkHttpClient okHttpClient) {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        log.trace("[FUCK] |- Bean [OkHttp3 Client Http Request Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @ConditionalOnClass(ApacheHttp5Client.class)
    @ConditionalOnUseHttpClient5RestClient
    @ConditionalOnMissingBean
    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory(CloseableHttpClient okHttpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(okHttpClient);
        log.trace("[FUCK] |- Bean [Http Components Http Request Factory] Auto Configure.");
        return factory;
    }

    @Bean
    @ConditionalOnUseSimpleRestClient
    @ConditionalOnMissingBean
    public ClientHttpRequestFactory SimpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        log.trace("[FUCK] |- Bean [Simple Client Http Request Factory] Auto Configure.");
        return factory;
    }

    /**
     * 使用 @LoadBalanced 注解表示使用 loadbalancer 实现客户端负载均衡
     *
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        /**
         * 默认的 RestTemplate 有个机制是请求状态码非200 就抛出异常，会中断接下来的操作。
         * 如果不想中断对结果数据得解析，可以通过覆盖默认的 ResponseErrorHandler ，
         * 对hasError修改下，让他一直返回true，即是不检查状态码及抛异常了
         */
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        };

        restTemplate.setErrorHandler(responseErrorHandler);

        log.trace("[FUCK] |- Bean [Rest Template] Auto Configure.");
        return restTemplate;
    }
}
