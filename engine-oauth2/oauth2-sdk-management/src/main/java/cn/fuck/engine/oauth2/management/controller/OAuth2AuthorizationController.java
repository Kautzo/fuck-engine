package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.oauth2.data.jpa.entity.HerodotusAuthorization;
import cn.fuck.engine.oauth2.data.jpa.service.HerodotusAuthorizationService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2 认证管理接口 </p>
 * @date : 2022/3/1 18:52
 */
@RestController
@RequestMapping("/authorize/authorization")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 认证管理接口")
})
public class OAuth2AuthorizationController extends BaseWriteableRestController<HerodotusAuthorization, String> {

    private final HerodotusAuthorizationService herodotusAuthorizationService;

    @Autowired
    public OAuth2AuthorizationController(HerodotusAuthorizationService herodotusAuthorizationService) {
        this.herodotusAuthorizationService = herodotusAuthorizationService;
    }

    @Override
    public WriteableService<HerodotusAuthorization, String> getWriteableService() {
        return this.herodotusAuthorizationService;
    }
}
