package cn.fuck.engine.supplier.upms.logic.repository.security;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.upms.logic.entity.security.SysAttribute;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

/**
 * <p>Description: SysAttributeRepository </p>
 * @date : 2021/8/4 6:48
 */
public interface SysAttributeRepository extends BaseRepository<SysAttribute, String> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<SysAttribute> findByAttributeIdIn(List<String> ids);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<SysAttribute> findAllByServiceId(String serviceId);
}
