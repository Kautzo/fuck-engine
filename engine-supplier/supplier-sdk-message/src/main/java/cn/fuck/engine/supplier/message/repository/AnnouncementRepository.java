package cn.fuck.engine.supplier.message.repository;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.message.entity.Announcement;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.Date;
import java.util.List;

/**
 * <p>Description: SystemAnnouncementRepository </p>
 * @date : 2022/12/7 22:07
 */
public interface AnnouncementRepository extends BaseRepository<Announcement, String> {

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    List<Announcement> findAllByCreateTimeAfter(Date stamp);
}
