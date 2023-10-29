package cn.fuck.engine.oauth2.management.service;

import cn.fuck.engine.oauth2.management.repository.OAuth2ProductRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.oauth2.management.entity.OAuth2Product;
import org.springframework.stereotype.Service;

/**
 * <p>Description: OAuth2ProductService </p>
 * @date : 2023/5/15 16:33
 */
@Service
public class OAuth2ProductService extends BaseService<OAuth2Product, String> {

    private final OAuth2ProductRepository productRepository;

    public OAuth2ProductService(OAuth2ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public BaseRepository<OAuth2Product, String> getRepository() {
        return productRepository;
    }
}
