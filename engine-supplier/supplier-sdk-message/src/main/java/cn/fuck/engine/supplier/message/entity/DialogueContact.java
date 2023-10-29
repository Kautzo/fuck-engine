package cn.fuck.engine.supplier.message.entity;

import cn.fuck.engine.message.core.constants.MessageConstants;
import cn.fuck.engine.supplier.message.domain.BaseSenderEntity;
import com.google.common.base.MoreObjects;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.UuidGenerator;

/**
 * <p>Description: 私信联系 </p>
 * <p>
 * 私信双相关系存储。
 * @date : 2022/12/7 11:03
 */
@Schema(name = "私信联系")
@Entity
@Table(name = "msg_dialogue_contact", indexes = {
        @Index(name = "msg_dialogue_contact_id_idx", columnList = "contact_id"),
        @Index(name = "msg_dialogue_contact_sid_idx", columnList = "sender_id"),
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_DIALOGUE_CONTACT)
public class DialogueContact extends BaseSenderEntity {

    @Schema(name = "联系ID")
    @Id
    @UuidGenerator
    @Column(name = "contact_id", length = 64)
    private String contactId;

    @Schema(name = "接收人ID")
    @Column(name = "receiver_id", length = 64)
    private String receiverId;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = MessageConstants.REGION_MESSAGE_DIALOGUE)
    @Schema(title = "对话ID")
    @ManyToOne
    @JoinColumn(name = "dialogue_id", nullable = false)
    private Dialogue dialogue;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Dialogue getDialogue() {
        return dialogue;
    }

    public void setDialogue(Dialogue dialogue) {
        this.dialogue = dialogue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("contactId", contactId)
                .add("receiverId", receiverId)
                .add("dialogue", dialogue)
                .toString();
    }
}
