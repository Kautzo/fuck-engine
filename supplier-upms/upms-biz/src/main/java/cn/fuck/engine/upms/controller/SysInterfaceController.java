package cn.fuck.engine.upms.controller;

import cn.fuck.engine.rest.core.controller.BaseController;
import cn.fuck.engine.upms.entity.SysInterface;
import cn.fuck.engine.upms.service.SysInterfaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 系统应用程序接口 Controller </p>
 */
@RestController
@RequestMapping("/security/interface")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统接口管理接口")
})
public class SysInterfaceController extends BaseController<SysInterfaceService, SysInterface, SysInterface, SysInterface, SysInterface, SysInterface> {

}
