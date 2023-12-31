package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.message.core.event.AccountReleaseFromCacheEvent;
import cn.fuck.engine.assistant.core.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2 扩展 接口 </p>
 */
@RestController
@RequestMapping("/oauth2")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 扩展接口")
})
public class OAuthSignOutController {

    private final OAuth2AuthorizationService authorizationService;
    private final ApplicationContext applicationContext;

    public OAuthSignOutController(OAuth2AuthorizationService authorizationService, ApplicationContext applicationContext) {
        this.authorizationService = authorizationService;
        this.applicationContext = applicationContext;
    }

    @Operation(summary = "注销OAuth2应用", description = "根据接收到的AccessToken,删除后端存储的Token信息,起到注销效果",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/x-www-form-urlencoded")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "accessToken", required = true, description = "Access Token"),
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "Basic Token"),
    })
    @PutMapping("/sign-out")
    public Result<String> signOut(@RequestParam(name = "accessToken") @NotBlank String accessToken, HttpServletRequest request) {
        OAuth2Authorization authorization = authorizationService.findByToken(accessToken, OAuth2TokenType.ACCESS_TOKEN);
        if (ObjectUtils.isNotEmpty(authorization)) {
            authorizationService.remove(authorization);
            //TODO 登出日志
//            complianceService.save(authorization.getPrincipalName(), authorization.getRegisteredClientId(), "退出系统", request);
            applicationContext.publishEvent(new AccountReleaseFromCacheEvent(authorization.getPrincipalName()));
        }
        return Result.success("注销成功");
    }
}
