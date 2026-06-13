package ru.mtuci.petunin.notify.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mtuci.petunin.notify.exception.ResourceNotFoundException;
import ru.mtuci.petunin.notify.model.dto.ReminderRequest;
import ru.mtuci.petunin.notify.model.dto.ReminderResponse;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.model.entity.Reminder;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;
import ru.mtuci.petunin.notify.model.enums.ReminderStatus;
import ru.mtuci.petunin.notify.repository.AppUserRepository;
import ru.mtuci.petunin.notify.repository.ReminderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final AppUserRepository userRepository;

    public ReminderService(ReminderRepository reminderRepository, AppUserRepository userRepository) {
        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReminderResponse create(ReminderRequest request) {
        AppUser recipient = userRepository.findById(request.getRecipientId())
                .orElseThrow(() -> new ResourceNotFoundException("Получатель не найден"));
        Reminder reminder = new Reminder();
        reminder.setSubject(request.getSubject());
        reminder.setText(request.getText());
        reminder.setChannel(request.getChannel());
        reminder.setStatus(ReminderStatus.CREATED);
        reminder.setCreatedAt(LocalDateTime.now());
        reminder.setRecipient(recipient);
        return toResponse(reminderRepository.save(reminder));
    }

    public List<ReminderResponse> findAll() {
        return reminderRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<ReminderResponse> findByStatus(ReminderStatus status) {
        return reminderRepository.findByStatus(status).stream().map(this::toResponse).toList();
    }

    public List<ReminderResponse> findByChannel(ReminderChannel channel) {
        return reminderRepository.findByChannel(channel).stream().map(this::toResponse).toList();
    }

    @Transactional
    public ReminderResponse markSent(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Напоминание не найдено"));
        reminder.setStatus(ReminderStatus.SENT);
        reminder.setSentAt(LocalDateTime.now());
        return toResponse(reminderRepository.save(reminder));
    }

    private ReminderResponse toResponse(Reminder reminder) {
        return new ReminderResponse(
                reminder.getId(),
                reminder.getSubject(),
                reminder.getText(),
                reminder.getChannel(),
                reminder.getStatus(),
                reminder.getRecipient().getId()
        );
    }
}
