package cn.fuck.engine.supplier.message.entity;

import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.message.core.enums.NotificationCategory;
import cn.fuck.engine.supplier.message.domain.BaseSenderEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 通知队列 </p>
 * @date : 2022/12/7 18:19
 */
@Schema(name = "通知队列")
@Entity
@Table(name = "msg_notification", indexes = {
        @Index(name = "msg_notification_id_idx", columnList = "queue_id"),
        @Index(name = "msg_notification_sid_idx", columnList = "user_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_NOTIFICATION)
public class Notification extends BaseSenderEntity {

    @Schema(name = "队列ID")
    @Id
    @UuidGenerator
    @Column(name = "queue_id", length = 64)
    private String queueId;

    @Schema(name = "是否已经读取", title = "false 未读，true 已读")
    @Column(name = "is_read")
    private Boolean read = false;

    @Schema(name = "用户ID")
    @Column(name = "user_id", length = 64)
    private String userId;

    @Schema(name = "公告内容")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Schema(name = "通知类别", title = "1. 公告，2.私信")
    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private NotificationCategory category = NotificationCategory.ANNOUNCEMENT;

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotificationCategory getCategory() {
        return category;
    }

    public void setCategory(NotificationCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("queueId", queueId)
                .add("read", read)
                .add("userId", userId)
                .add("content", content)
                .add("category", category)
                .toString();
    }
}
