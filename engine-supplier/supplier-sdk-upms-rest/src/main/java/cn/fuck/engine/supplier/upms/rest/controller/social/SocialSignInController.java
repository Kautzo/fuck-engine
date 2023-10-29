package cn.fuck.engine.supplier.upms.rest.controller.social;

import cn.fuck.engine.assistant.core.domain.AccessPrincipal;
import cn.fuck.engine.assistant.core.domain.Result;
import cn.fuck.engine.oauth2.core.definition.domain.FuckUser;
import cn.fuck.engine.oauth2.core.definition.handler.AbstractSocialAuthenticationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 社交用户登录接口 </p>
 * @date : 2022/6/20 18:19
 */
@RestController
@RequestMapping("/security/social")
@Tag(name = "社交用户登录接口")
public class SocialSignInController {

    private final AbstractSocialAuthenticationHandler socialAuthenticationHandler;

    @Autowired
    public SocialSignInController(AbstractSocialAuthenticationHandler socialAuthenticationHandler) {
        this.socialAuthenticationHandler = socialAuthenticationHandler;
    }

    @Operation(summary = "社交登录用户信息查询", description = "根据不同的source查询对应社交用户的信息")
    @Parameters({
            @Parameter(name = "source", required = true, description = "系统用户名", in = ParameterIn.PATH),
    })
    @RequestMapping("/sign-in/{source}")
    public Result<FuckUser> findUserDetailsBySocial(@PathVariable("source") String source, AccessPrincipal accessPrincipal) {
        FuckUser fuckUser = this.socialAuthenticationHandler.authentication(source, accessPrincipal);
        if (ObjectUtils.isNotEmpty(fuckUser)) {
            return Result.success("社交登录成功", fuckUser);
        } else {
            return Result.failure("社交登录失败，未能查到用户数据");
        }
    }
}
