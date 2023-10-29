package cn.fuck.engine.supplier.message.entity;

import cn.fuck.engine.assistant.core.definition.constants.DefaultConstants;
import cn.fuck.engine.assistant.core.definition.domain.AbstractEntity;
import cn.fuck.engine.message.core.constants.MessageConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

/**
 * <p>Description: 信息拉取标记 </p>
 * @date : 2022/12/6 21:34
 */
@Schema(name = "拉取标记")
@Entity
@Table(name = "msg_pull_stamp", indexes = {
        @Index(name = "msg_pull_stamp_id_idx", columnList = "stamp_id"),
        @Index(name = "msg_pull_stamp_sid_idx", columnList = "user_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_PULL_STAMP)
public class PullStamp extends AbstractEntity {

    @Id
    @UuidGenerator
    @Column(name = "stamp_id", length = 64)
    private String stampId;

    @Schema(name = "用户ID")
    @Column(name = "user_id", length = 64)
    private String userId;

    @Schema(name = "来源", title = "预留字段，以备支持不同端的情况")
    @Column(name = "source", length = 50)
    private String source;

    @Schema(title = "上次拉取时间")
    @Column(name = "latest_pull_time", updatable = false)
    @JsonFormat(pattern = DefaultConstants.DATE_TIME_FORMAT)
    private Date latestPullTime = new Date();

    public String getStampId() {
        return stampId;
    }

    public void setStampId(String stampId) {
        this.stampId = stampId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getLatestPullTime() {
        return latestPullTime;
    }

    public void setLatestPullTime(Date latestPullTime) {
        this.latestPullTime = latestPullTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("stampId", stampId)
                .add("userId", userId)
                .add("source", source)
                .add("latestPullTime", latestPullTime)
                .toString();
    }
}
