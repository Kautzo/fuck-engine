package cn.fuck.engine.supplier.message.service;

import cn.fuck.engine.supplier.message.entity.Announcement;
import cn.fuck.engine.supplier.message.repository.AnnouncementRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Description: SystemAnnouncementService </p>
 * @date : 2022/12/7 22:11
 */
@Service
public class AnnouncementService extends BaseService<Announcement, String> {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public BaseRepository<Announcement, String> getRepository() {
        return announcementRepository;
    }

    public List<Announcement> pullAnnouncements(Date stamp) {
        return announcementRepository.findAllByCreateTimeAfter(stamp);
    }
}
