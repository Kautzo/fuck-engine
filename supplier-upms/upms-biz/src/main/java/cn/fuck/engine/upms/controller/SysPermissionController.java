package cn.fuck.engine.upms.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.annotation.AccessLimited;
import cn.fuck.engine.rest.core.controller.BaseController;
import cn.fuck.engine.upms.entity.SysPermission;
import cn.fuck.engine.upms.service.SysPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: SysPermissionController </p>
 */
@RestController
@RequestMapping("/security/permission")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统权限管理接口")
})
public class SysPermissionController extends BaseController<SysPermissionService, SysPermission, SysPermission, SysPermission, SysPermission, SysPermission> {

    @AccessLimited
    @Operation(summary = "获取全部权限", description = "获取全部权限数据列表")
    @GetMapping("/list")
    public Result<List<SysPermission>> list() {
        List<SysPermission> sysAuthorities = baseService.list();
        return Result.content(sysAuthorities);
    }

    @Override
    public Boolean handlerDelete(List<String> ids) {
        return baseService.handlerDelete(ids);
    }
}
