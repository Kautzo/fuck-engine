package cn.fuck.engine.supplier.upms.rest.controller.security;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysDefaultRole;
import cn.fuck.engine.supplier.upms.logic.service.security.SysDefaultRoleService;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 系统默认角色管理接口 </p>
 * @date : 2021/7/21 16:12
 */
@RestController
@RequestMapping("/security/default-role")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统默认角色管理接口")
})
public class SysDefaultRoleController extends BaseWriteableRestController<SysDefaultRole, String> {

    private final SysDefaultRoleService sysDefaultRoleService;

    public SysDefaultRoleController(SysDefaultRoleService sysDefaultRoleService) {
        this.sysDefaultRoleService = sysDefaultRoleService;
    }

    @Override
    public WriteableService<SysDefaultRole, String> getWriteableService() {
        return this.sysDefaultRoleService;
    }

    @Operation(summary = "设置默认角色", description = "给不同的登录场景设置不同的默认角色",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/x-www-form-urlencoded")),
            responses = {@ApiResponse(description = "已保存数据", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "defaultId", required = true, description = "默认角色类型ID"),
            @Parameter(name = "roleId", required = true, description = "设置的角色ID")
    })
    @PutMapping
    public Result<SysDefaultRole> assign(@RequestParam(name = "defaultId") @NotBlank String defaultId, @RequestParam(name = "roleId") @NotBlank String roleId) {
        SysDefaultRole sysDefaultRole = sysDefaultRoleService.assign(defaultId, roleId);
        return result(sysDefaultRole);
    }
}
