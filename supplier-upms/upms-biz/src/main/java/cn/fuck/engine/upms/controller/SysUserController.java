package cn.fuck.engine.upms.controller;

import cn.fuck.engine.rest.core.controller.BaseController;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.rest.core.annotation.Crypto;
import cn.fuck.engine.upms.dto.SysUserSaveDTO;
import cn.fuck.engine.upms.entity.SysUser;
import cn.fuck.engine.upms.service.SysUserService;
import cn.fuck.engine.upms.vo.SysUserResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: 系统用户接口 </p>
 */
@RestController
@RequestMapping("/security/user")
@Tags({
        @Tag(name = "用户安全管理接口"),
        @Tag(name = "系统用户管理接口")
})
public class SysUserController extends BaseController<SysUserService, SysUser, SysUserSaveDTO, SysUser, SysUser, SysUserResultVO> {

    /**
     * 给用户分配角色
     *
     * @param userId  用户Id
     * @param roleIds 角色Id数组
     * @return {@link Result}
     */
    @Operation(summary = "给用户分配角色", description = "给用户分配角色")
    @PutMapping("/updateUserRole")
    public Result<?> updateUserRole(@RequestParam(name = "userId") String userId,
                                    @RequestParam(name = "roleIds") String[] roleIds) {
        baseService.updateUserRole(userId, List.of(roleIds));
        return Result.success();
    }

    @Operation(summary = "修改密码", description = "修改用户使用密码，默认使用加密请求传输")
    @Crypto(responseEncrypt = false)
    @PutMapping("/changePassword")
    public Result<?> changePassword(@RequestParam(name = "userId") String userId,
                                    @RequestParam(name = "password") String password) {
        baseService.changePassword(userId, password);
        return Result.success();
    }

    @Operation(summary = "根据用户名查询系统用户", description = "通过username查询系统用户数据")
    @GetMapping("/sign-in/{userName}")
    public Result<SysUser> getByUserName(@PathVariable("userName") String userName) {
        SysUser sysUser = baseService.getByUserName(userName);
        return Result.content(sysUser);
    }

    @Override
    public Boolean handlerDelete(List<String> ids) {
        return baseService.handlerDelete(ids);
    }
}
