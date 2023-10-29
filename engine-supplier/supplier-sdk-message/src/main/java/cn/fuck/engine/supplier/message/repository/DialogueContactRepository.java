package cn.fuck.engine.supplier.message.repository;

import cn.fuck.engine.assistant.core.exception.transaction.TransactionalRollbackException;
import cn.fuck.engine.data.core.repository.BaseRepository;
import cn.fuck.engine.supplier.message.entity.DialogueContact;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>Description: PersonalContactRepository </p>
 * @date : 2022/12/7 22:05
 */
public interface DialogueContactRepository extends BaseRepository<DialogueContact, String> {

    @Transactional(rollbackFor = TransactionalRollbackException.class)
    @Modifying
    @Query("delete from DialogueContact c where c.dialogue.dialogueId = :id")
    void deleteAllByDialogueId(@Param("id") String dialogueId);

    @QueryHints(@QueryHint(name = AvailableHints.HINT_CACHEABLE, value = "true"))
    Optional<DialogueContact> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
