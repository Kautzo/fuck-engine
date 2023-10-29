package cn.fuck.engine.supplier.upms.rest.controller.security;

import cn.fuck.engine.supplier.upms.logic.entity.security.SysInterface;
import cn.fuck.engine.supplier.upms.logic.service.security.SysInterfaceService;
import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 系统应用程序接口 Controller </p>
 * @date : 2023/3/9 5:05
 */
@RestController
@RequestMapping("/security/interface")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统接口管理接口")
})
public class SysInterfaceController extends BaseWriteableRestController<SysInterface, String> {

    private final SysInterfaceService sysInterfaceService;

    public SysInterfaceController(SysInterfaceService sysInterfaceService) {
        this.sysInterfaceService = sysInterfaceService;
    }

    @Override
    public WriteableService<SysInterface, String> getWriteableService() {
        return sysInterfaceService;
    }
}
