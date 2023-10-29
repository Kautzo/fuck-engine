package cn.fuck.engine.supplier.message.repository;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.message.entity.PullStamp;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Optional;

/**
 * <p>Description: PullStampRepository </p>
 * @date : 2022/12/6 21:56
 */
public interface PullStampRepository extends BaseRepository<PullStamp, String> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<PullStamp> findByUserId(String userId);
}
