package ru.mtuci.petunin.notify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.petunin.notify.model.entity.Reminder;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;
import ru.mtuci.petunin.notify.model.enums.ReminderStatus;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByStatus(ReminderStatus status);
    List<Reminder> findByChannel(ReminderChannel channel);
    List<Reminder> findByRecipientId(Long recipientId);
}
