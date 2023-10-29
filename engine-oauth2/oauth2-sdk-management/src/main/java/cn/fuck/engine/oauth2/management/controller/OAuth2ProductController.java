package cn.fuck.engine.oauth2.management.controller;

import cn.fuck.engine.data.core.service.WriteableService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Product;
import cn.fuck.engine.oauth2.management.service.OAuth2ProductService;
import cn.fuck.engine.rest.core.controller.BaseWriteableRestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: OAuth2ProductController </p>
 * @date : 2023/5/15 16:37
 */
@RestController
@RequestMapping("/authorize/product")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "物联网管理接口"),
        @Tag(name = "物联网产品接口")
})
public class OAuth2ProductController extends BaseWriteableRestController<OAuth2Product, String> {

    private final OAuth2ProductService iotProductService;

    public OAuth2ProductController(OAuth2ProductService iotProductService) {
        this.iotProductService = iotProductService;
    }

    @Override
    public WriteableService<OAuth2Product, String> getWriteableService() {
        return iotProductService;
    }
}
