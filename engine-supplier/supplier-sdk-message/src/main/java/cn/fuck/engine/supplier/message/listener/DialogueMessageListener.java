package cn.fuck.engine.supplier.message.listener;

import cn.fuck.engine.supplier.message.entity.DialogueDetail;
import cn.fuck.engine.message.core.domain.DialogueMessage;
import cn.fuck.engine.message.core.event.LocalSendDialogueMessageEvent;
import cn.fuck.engine.supplier.message.service.DialogueDetailService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>Description: 对话消息监听 </p>
 * <p>
 * 解耦模块之间的依赖关系
 * @date : 2023/3/11 18:49
 */
@Component
public class DialogueMessageListener implements ApplicationListener<LocalSendDialogueMessageEvent> {

    private final DialogueDetailService dialogueDetailService;

    public DialogueMessageListener(DialogueDetailService dialogueDetailService) {
        this.dialogueDetailService = dialogueDetailService;
    }

    @Override
    public void onApplicationEvent(LocalSendDialogueMessageEvent event) {
        if (ObjectUtils.isNotEmpty(event)) {
            DialogueMessage dialogueMessage = event.getData();
            if (ObjectUtils.isNotEmpty(dialogueMessage)) {
                DialogueDetail dialogueDetail = convertDialogueMessageToDialogueDetail(dialogueMessage);
                dialogueDetailService.save(dialogueDetail);
            }
        }
    }

    private DialogueDetail convertDialogueMessageToDialogueDetail(DialogueMessage dialogueMessage) {
        DialogueDetail dialogueDetail = new DialogueDetail();
        dialogueDetail.setDetailId(dialogueMessage.getDetailId());
        dialogueDetail.setReceiverId(dialogueMessage.getReceiverId());
        dialogueDetail.setReceiverName(dialogueMessage.getReceiverName());
        dialogueDetail.setReceiverAvatar(dialogueMessage.getReceiverAvatar());
        dialogueDetail.setContent(dialogueMessage.getContent());
        dialogueDetail.setDialogueId(dialogueMessage.getDialogueId());
        dialogueDetail.setSenderId(dialogueMessage.getSenderId());
        dialogueDetail.setSenderName(dialogueMessage.getSenderName());
        dialogueDetail.setSenderAvatar(dialogueMessage.getSenderAvatar());
        return dialogueDetail;
    }
}
