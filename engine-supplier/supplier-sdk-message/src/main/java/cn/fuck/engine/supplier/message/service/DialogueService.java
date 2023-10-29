package cn.fuck.engine.supplier.message.service;

import cn.fuck.engine.supplier.message.entity.Dialogue;
import cn.fuck.engine.supplier.message.repository.DialogueRepository;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.data.core.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: PersonalDialogueService </p>
 * @date : 2022/12/7 22:09
 */
@Service
public class DialogueService extends BaseService<Dialogue, String> {

    private final DialogueRepository dialogueRepository;

    public DialogueService(DialogueRepository dialogueRepository) {
        this.dialogueRepository = dialogueRepository;
    }

    @Override
    public BaseRepository<Dialogue, String> getRepository() {
        return dialogueRepository;
    }

    public Dialogue createDialogue(String content) {
        Dialogue dialogue = new Dialogue();
        dialogue.setLatestNews(content);
        return this.save(dialogue);
    }

    public Dialogue updateDialogue(String dialogueId, String content) {
        Dialogue dialogue = this.findById(dialogueId);
        dialogue.setLatestNews(content);
        return this.save(dialogue);
    }
}
