package cn.fuck.engine.upms.api;

import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.annotation.Inner;
import cn.fuck.engine.upms.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${fuck.endpoint.upms-service-name:cloud-upms}", path = "/security/user")
public interface SysUserApi {

    @Inner
    @GetMapping("/getByUserName")
    Result<SysUser> getByUserName(@RequestParam("userName") String userName);


}
