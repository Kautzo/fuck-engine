package cn.fuck.engine.message.core.domain;

import cn.fuck.engine.data.core.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Description: 对话消息传递对象 </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DialogueMessage extends BaseEntity {

    @Schema(name = "对话详情ID")
    private String detailId;

    @Schema(name = "接收人ID")
    private String receiverId;

    @Schema(name = "接收人名称", title = "冗余信息，增加该字段减少重复查询")
    private String receiverName;

    @Schema(name = "公告内容")
    private String content;

    @Schema(name = "对话ID")
    private String dialogueId;

    @Schema(name = "发送人ID")
    private String senderId;

    @Schema(name = "发送人名称", title = "冗余信息，增加该字段减少重复查询")
    private String senderName;

}
