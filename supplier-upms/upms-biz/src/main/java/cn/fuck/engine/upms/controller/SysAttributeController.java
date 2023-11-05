package cn.fuck.engine.upms.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.controller.BaseController;
import cn.fuck.engine.upms.entity.SysAttribute;
import cn.fuck.engine.upms.service.SysAttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: 系统安全属性 Controller </p>
 */
@RestController
@RequestMapping("/security/attribute")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统属性管理接口")
})
public class SysAttributeController extends BaseController<SysAttributeService, SysAttribute, SysAttribute, SysAttribute, SysAttribute, SysAttribute> {

    @Operation(summary = "给属性分配权限", description = "给属性分配权限，方便权限数据操作")
    @Parameters({
            @Parameter(name = "attributeId", required = true, description = "attributeId"),
            @Parameter(name = "permissions[]", required = true, description = "角色对象组成的数组")
    })
    @PutMapping("/updateAttributePermission")
    public Result<?> updateAttributePermission(@RequestParam(name = "attributeId") String attributeId,
                                       @RequestParam(name = "permissions[]") String[] permissionIds) {
        baseService.updateAttributePermission(attributeId, List.of(permissionIds));
        return Result.success();
    }

}