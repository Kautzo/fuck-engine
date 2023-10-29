package cn.fuck.engine.supplier.message.repository;

import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.message.entity.DialogueDetail;

/**
 * <p>Description: PersonalDialogueDetailRepository </p>
 * @date : 2022/12/7 22:06
 */
public interface DialogueDetailRepository extends BaseRepository<DialogueDetail, String> {

    void deleteAllByDialogueId(String dialogueId);
}
