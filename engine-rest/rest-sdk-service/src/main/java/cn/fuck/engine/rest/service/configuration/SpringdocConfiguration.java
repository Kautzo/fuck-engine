package cn.fuck.engine.rest.service.configuration;

import cn.fuck.engine.rest.condition.properties.SwaggerProperties;
import cn.fuck.engine.assistant.core.annotation.ConditionalOnSwaggerEnabled;
import cn.fuck.engine.assistant.core.context.ServiceContextHolder;
import cn.fuck.engine.assistant.core.definition.constants.BaseConstants;
import cn.fuck.engine.rest.core.definition.OpenApiServerResolver;
import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description: 服务信息配置类 </p>
 *
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnSwaggerEnabled
@EnableConfigurationProperties(SwaggerProperties.class)
@SecuritySchemes({
        @SecurityScheme(name = BaseConstants.OPEN_API_SECURITY_SCHEME_BEARER_NAME,
                type = SecuritySchemeType.OAUTH2,
                bearerFormat = "JWT", scheme = "bearer",
                flows = @OAuthFlows(
                        password = @OAuthFlow(authorizationUrl = "${fuck.endpoint.authorization-uri}",
                                tokenUrl = "${fuck.endpoint.access-token-uri}",
                                refreshUrl = "${fuck.endpoint.access-token-uri}",
                                scopes = @OAuthScope(name = "all")),
                        clientCredentials = @OAuthFlow(authorizationUrl = "${fuck.endpoint.authorization-uri}",
                                tokenUrl = "${fuck.endpoint.access-token-uri}",
                                refreshUrl = "${fuck.endpoint.access-token-uri}",
                                scopes = @OAuthScope(name = "all"))
//                        authorizationCode = @OAuthFlow(authorizationUrl = "${fuck.platform.endpoint.user-authorization-uri}",
//                                tokenUrl = "${fuck.platform.endpoint.access-token-uri}",
//                                refreshUrl = "${fuck.platform.endpoint.access-token-uri}",
//                                scopes = @OAuthScope(name = "all"))
                )),
})
public class SpringdocConfiguration {

    @PostConstruct
    public void postConstruct() {
        log.debug("[FUCK] |- Module [Open Api] Auto Configure.");
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenApiServerResolver openApiServerResolver() {
        OpenApiServerResolver resolver = () -> {
            Server server = new Server();
            server.setUrl(ServiceContextHolder.getInstance().getUrl());
            return ImmutableList.of(server);
        };
        log.trace("[FUCK] |- Bean [Open Api Server Resolver] Auto Configure.");
        return resolver;
    }

    @Bean
    public GroupedOpenApi defaultOpenApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("cn.fuck")
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI createOpenApi(OpenApiServerResolver openApiServerResolver) {
        return new OpenAPI()
                .servers(openApiServerResolver.getServers())
                .info(new Info().title("FUCK Cloud")
                        .description("FUCK Cloud")
                        .version("Swagger V3")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/")))
                .externalDocs(new ExternalDocumentation()
                        .description("FUCK Cloud Documentation"));
    }
}
