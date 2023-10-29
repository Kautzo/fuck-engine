package cn.fuck.engine.supplier.upms.rest.controller.hr;

import cn.fuck.engine.supplier.upms.logic.entity.hr.SysOwnership;
import cn.fuck.engine.supplier.upms.logic.service.hr.SysOwnershipService;
import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 人事归属Controller </p>
 * @date : 2021/7/15 16:36
 */
@RestController
@RequestMapping("/hr/ownership")
@Tag(name = "人事归属管理接口")
public class SysOwnershipController extends BaseWriteableRestController<SysOwnership, String> {

    private final SysOwnershipService sysOwnershipService;

    public SysOwnershipController(SysOwnershipService sysOwnershipService) {
        this.sysOwnershipService = sysOwnershipService;
    }

    @Override
    public WriteableService<SysOwnership, String> getWriteableService() {
        return this.sysOwnershipService;
    }
}
