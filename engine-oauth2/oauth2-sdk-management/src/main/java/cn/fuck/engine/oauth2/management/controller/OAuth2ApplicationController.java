package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.management.dto.OAuth2ApplicationDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Application;
import cn.fuck.engine.oauth2.management.service.OAuth2ApplicationService;
import cn.fuck.engine.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * <p>Description: OAuth2应用管理接口 </p>
 */
@RestController
@RequestMapping("/authorize/application")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 应用管理接口")
})
public class OAuth2ApplicationController
        extends BaseController<OAuth2ApplicationService, OAuth2Application, OAuth2ApplicationDTO, OAuth2ApplicationDTO, OAuth2Application, OAuth2Application> {

    @Operation(summary = "给应用分配Scope", description = "给应用分配Scope")
    @PutMapping("/updateScope")
    public Result<?> updateScope(@RequestParam(name = "applicationId") String applicationId,
                                 @RequestParam(name = "scopeIds") String[] scopeIds) {
        baseService.updateScope(applicationId, Set.of(scopeIds));
        return Result.success();
    }

}
