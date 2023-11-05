package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.management.dto.OAuth2DeviceDTO;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceService;
import cn.fuck.engine.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/authorize/device")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "物联网管理接口"),
        @Tag(name = "物联网设备接口")
})
public class OAuth2DeviceController extends BaseController<OAuth2DeviceService, OAuth2Device, OAuth2DeviceDTO, OAuth2DeviceDTO, OAuth2Device, OAuth2Device> {


    @Operation(summary = "给设备分配Scope", description = "给设备分配Scope")
    @PutMapping("/updateScope")
    public Result<?> updateScope(@RequestParam(name = "deviceId") String deviceId,
                                 @RequestParam(name = "scopeIds") String[] scopeIds) {
        baseService.updateScope(deviceId, Set.of(scopeIds));
        return Result.success();
    }

}
