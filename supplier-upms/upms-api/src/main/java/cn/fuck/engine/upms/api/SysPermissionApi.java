package cn.fuck.engine.upms.api;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.annotation.Inner;
import cn.fuck.engine.upms.entity.SysPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "${fuck.endpoint.upms-service-name:cloud-upms}", path = "/security/permission")
public interface SysPermissionApi {

    @Inner
    @GetMapping("/list")
    Result<List<SysPermission>> list();
}
