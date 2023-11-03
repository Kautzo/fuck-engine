package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.management.dto.OAuth2ScopeDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Scope;
import cn.fuck.engine.oauth2.management.service.OAuth2ScopeService;
import cn.fuck.engine.rest.core.annotation.AccessLimited;
import cn.fuck.engine.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> Description : OauthScopesController </p>
 */
@RestController
@RequestMapping("/authorize/scope")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 权限范围管理接口")
})
public class OAuth2ScopeController extends BaseController<OAuth2ScopeService, OAuth2Scope, OAuth2Scope, OAuth2Scope, OAuth2Scope, OAuth2Scope> {

    @Operation(summary = "给Scope分配权限", description = "给Scope分配权限")
    @PostMapping("/updateScopePermission")
    public Result<OAuth2Scope> updateScopePermission(@RequestBody OAuth2ScopeDTO scopeDTO) {
       baseService.updateScopePermission(scopeDTO);
        return Result.success();
    }

    @AccessLimited
    @Operation(summary = "获取全部范围", description = "获取全部范围")
    @GetMapping("/list")
    public Result<List<OAuth2Scope>> list() {
        List<OAuth2Scope> oAuth2Scopes = baseService.list();
        return Result.content(oAuth2Scopes);
    }

    @AccessLimited
    @Operation(summary = "根据范围代码查询应用范围", description = "根据范围代码查询应用范围")
    @GetMapping("/getByScopeCode")
    public Result<OAuth2Scope> getByScopeCode(@RequestParam("scopeCode") String scopeCode) {
        OAuth2Scope scope = baseService.getByScopeCode(scopeCode);
        return Result.content(scope);
    }

    @Override
    public Boolean handlerDelete(List<String> ids) {
        return baseService.handlerDelete(ids);
    }
}
