package cn.fuck.engine.message.core.event;

import cn.fuck.engine.message.core.definition.LocalApplicationEvent;
import cn.fuck.engine.message.core.domain.DialogueMessage;

import java.time.Clock;

/**
 * <p>Description: 本地发送对话消息事件 </p>
 * @date : 2023/3/11 18:40
 */
public class LocalSendDialogueMessageEvent extends LocalApplicationEvent<DialogueMessage> {

    public LocalSendDialogueMessageEvent(DialogueMessage data) {
        super(data);
    }

    public LocalSendDialogueMessageEvent(DialogueMessage data, Clock clock) {
        super(data, clock);
    }
}
