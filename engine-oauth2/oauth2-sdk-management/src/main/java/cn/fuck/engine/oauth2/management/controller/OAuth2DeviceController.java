package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Device;
import cn.fuck.engine.oauth2.management.service.OAuth2DeviceService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2DeviceController </p>
 * @date : 2023/5/15 16:58
 */
@RestController
@RequestMapping("/authorize/device")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "物联网管理接口"),
        @Tag(name = "物联网设备接口")
})
public class OAuth2DeviceController extends BaseWriteableRestController<OAuth2Device, String> {

    private final OAuth2DeviceService deviceService;

    public OAuth2DeviceController(OAuth2DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public WriteableService<OAuth2Device, String> getWriteableService() {
        return deviceService;
    }

    @Operation(summary = "给设备分配Scope", description = "给设备分配Scope")
    @Parameters({
            @Parameter(name = "deviceId", required = true, description = "设备ID"),
            @Parameter(name = "scopes[]", required = true, description = "Scope对象组成的数组")
    })
    @PutMapping
    public Result<OAuth2Device> authorize(@RequestParam(name = "deviceId") String deviceId, @RequestParam(name = "scopes[]") String[] scopes) {
        OAuth2Device device = deviceService.authorize(deviceId, scopes);
        return result(device);
    }
}
