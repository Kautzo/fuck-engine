package cn.fuck.engine.supplier.upms.rest.controller.security;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysPermission;
import cn.fuck.engine.supplier.upms.logic.service.security.SysPermissionService;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.rest.core.annotation.AccessLimited;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: SysPermissionController </p>
 * @date : 2020/4/10 13:21
 */
@RestController
@RequestMapping("/security/permission")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统权限管理接口")
})
public class SysPermissionController extends BaseWriteableRestController<SysPermission, String> {

    private final SysPermissionService sysPermissionService;

    public SysPermissionController(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }

    @Override
    public WriteableService<SysPermission, String> getWriteableService() {
        return this.sysPermissionService;
    }

    @AccessLimited
    @Operation(summary = "获取全部权限", description = "获取全部权限数据列表",
            responses = {
                    @ApiResponse(description = "全部数据列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
                    @ApiResponse(responseCode = "204", description = "查询成功，未查到数据"),
                    @ApiResponse(responseCode = "500", description = "查询失败")
            })
    @GetMapping("/list")
    public Result<List<SysPermission>> findAll() {
        List<SysPermission> sysAuthorities = sysPermissionService.findAll();
        return result(sysAuthorities);
    }
}
