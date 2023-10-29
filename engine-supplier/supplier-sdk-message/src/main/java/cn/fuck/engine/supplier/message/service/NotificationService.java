package cn.fuck.engine.supplier.message.service;

import cn.fuck.engine.supplier.message.entity.Announcement;
import cn.fuck.engine.supplier.message.entity.Notification;
import cn.fuck.engine.supplier.message.entity.PullStamp;
import cn.fuck.engine.supplier.message.repository.NotificationRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import cn.fuck.engine.message.core.enums.NotificationCategory;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: NotificationQueueService </p>
 * @date : 2022/12/7 22:08
 */
@Service
public class NotificationService extends BaseService<Notification, String> {

    private final NotificationRepository notificationRepository;
    private final PullStampService pullStampService;
    private final AnnouncementService announcementService;

    public NotificationService(NotificationRepository notificationRepository, PullStampService pullStampService, AnnouncementService announcementService) {
        this.notificationRepository = notificationRepository;
        this.pullStampService = pullStampService;
        this.announcementService = announcementService;
    }

    @Override
    public BaseRepository<Notification, String> getRepository() {
        return notificationRepository;
    }

    public void pullAnnouncements(String userId) {
        PullStamp pullStamp = pullStampService.getPullStamp(userId);
        List<Announcement> systemAnnouncements = announcementService.pullAnnouncements(pullStamp.getLatestPullTime());
        if (CollectionUtils.isNotEmpty(systemAnnouncements)) {
            List<Notification> notificationQueues = convertAnnouncementsToNotifications(userId, systemAnnouncements);
            this.saveAll(notificationQueues);
        }
    }

    public Page<Notification> findByCondition(int pageNumber, int pageSize, String userId, NotificationCategory category, Boolean read) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<Notification> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("userId"), userId));

            if (ObjectUtils.isNotEmpty(category)) {
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }

            if (ObjectUtils.isNotEmpty(read)) {
                predicates.add(criteriaBuilder.equal(root.get("read"), read));
            }

            Predicate[] predicateArray = new Predicate[predicates.size()];
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(predicateArray)));
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
            return criteriaQuery.getRestriction();
        };

        return this.findByPage(specification, pageable);
    }

    private List<Notification> convertAnnouncementsToNotifications(String userId, List<Announcement> announcements) {
        return announcements.stream().map(announcement -> convertAnnouncementToNotification(userId, announcement)).collect(Collectors.toList());
    }

    private Notification convertAnnouncementToNotification(String userId, Announcement announcement) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setContent(announcement.getContent());
        notification.setSenderId(announcement.getSenderId());
        notification.setSenderName(announcement.getSenderName());
        notification.setSenderAvatar(announcement.getSenderAvatar());
        notification.setCategory(NotificationCategory.ANNOUNCEMENT);
        return notification;
    }

    public int setAllRead(String userId) {
        return notificationRepository.updateAllRead(userId);
    }
}
