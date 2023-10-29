package cn.fuck.engine.oauth2.data.jpa.repository;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.oauth2.data.jpa.entity.HerodotusRegisteredClient;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

/**
 * <p>Description: HerodotusRegisteredClientRepository </p>
 * @date : 2022/2/25 21:05
 */
public interface HerodotusRegisteredClientRepository extends BaseRepository<HerodotusRegisteredClient, String> {

    /**
     * 根据 ClientId 查询 RegisteredClient
     *
     * @param clientId OAuth2 客户端ID
     * @return OAuth2 客户端配置
     */
    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<HerodotusRegisteredClient> findByClientId(String clientId);
}
