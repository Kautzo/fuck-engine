package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.oauth2.data.entity.FuckAuthorization;
import cn.fuck.engine.oauth2.data.service.FuckAuthorizationService;
import cn.fuck.engine.rest.core.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2 认证管理接口 </p>
 */
@RestController
@RequestMapping("/authorize/authorization")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 认证管理接口")
})
public class OAuth2AuthorizationController extends BaseController<FuckAuthorizationService, FuckAuthorization, FuckAuthorization, FuckAuthorization, FuckAuthorization, FuckAuthorization> {

}
