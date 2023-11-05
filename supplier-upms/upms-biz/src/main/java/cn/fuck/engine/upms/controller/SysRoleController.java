package cn.fuck.engine.upms.controller;

import cn.fuck.engine.rest.core.controller.BaseController;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.annotation.AccessLimited;
import cn.fuck.engine.upms.entity.SysRole;
import cn.fuck.engine.upms.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: SysRoleController </p>
 */
@RestController
@RequestMapping("/security/role")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统角色管理接口")
})
public class SysRoleController extends BaseController<SysRoleService, SysRole, SysRole, SysRole, SysRole, SysRole> {

    @AccessLimited
    @Operation(summary = "根据角色代码查询角色", description = "根据输入的角色代码，查询对应的角色")
    @GetMapping("/getByRoleCode")
    public Result<SysRole> getByRoleCode(@RequestParam("roleCode") String roleCode) {
        SysRole sysRole = baseService.getByRoleCode(roleCode);
        return Result.content(sysRole);
    }

    @AccessLimited
    @Operation(summary = "获取全部角色", description = "获取全部角色数据列表")
    @GetMapping("/list")
    public Result<List<SysRole>> list() {
        List<SysRole> sysAuthorities = baseService.list();
        return Result.content(sysAuthorities);
    }

    @Operation(summary = "给角色赋予权限", description = "为角色赋予权限")
    @Parameters({
            @Parameter(name = "roleId", required = true, description = "角色ID"),
            @Parameter(name = "permissionIds[]", required = true, description = "权限对象组成的数组")
    })
    @PutMapping("/updateRolePermission")
    public Result<?> updateRolePermission(@RequestParam(name = "roleId") String roleId,
                                          @RequestParam(name = "permissionIds[]") String[] permissionIds) {
        baseService.updateRolePermission(roleId, List.of(permissionIds));
        return Result.success();
    }

}
