package cn.fuck.engine.supplier.message.repository;

import cn.fuck.engine.assistant.core.exception.transaction.TransactionalRollbackException;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.message.entity.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Description: NotificationQueueRepository </p>
 * @date : 2022/12/7 22:03
 */
public interface NotificationRepository extends BaseRepository<Notification, String> {

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query("update Notification n set n.read = true where n.userId = :userId")
    int updateAllRead(@Param("userId") String userId);
}
