package cn.fuck.engine.supplier.upms.rest.controller.security;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysAttribute;
import cn.fuck.engine.supplier.upms.logic.service.security.SysAttributeService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 系统安全属性 Controller </p>
 * @date : 2022/6/20 18:12
 */
@RestController
@RequestMapping("/security/attribute")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统属性管理接口")
})
public class SysAttributeController extends BaseWriteableRestController<SysAttribute, String> {

    private final SysAttributeService sysAttributeService;

    public SysAttributeController(SysAttributeService sysAttributeService) {
        this.sysAttributeService = sysAttributeService;
    }

    @Override
    public WriteableService<SysAttribute, String> getWriteableService() {
        return this.sysAttributeService;
    }

    @Operation(summary = "给属性分配权限", description = "给属性分配权限，方便权限数据操作",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/x-www-form-urlencoded")),
            responses = {@ApiResponse(description = "已保存数据", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "attributeId", required = true, description = "attributeId"),
            @Parameter(name = "permissions[]", required = true, description = "角色对象组成的数组")
    })
    @PutMapping
    public Result<SysAttribute> assign(@RequestParam(name = "attributeId") String attributeId, @RequestParam(name = "permissions[]") String[] permissions) {
        SysAttribute sysAttribute = sysAttributeService.assign(attributeId, permissions);
        return result(sysAttribute);
    }
}