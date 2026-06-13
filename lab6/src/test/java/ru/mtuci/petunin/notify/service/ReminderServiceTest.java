package ru.mtuci.petunin.notify.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mtuci.petunin.notify.exception.ResourceNotFoundException;
import ru.mtuci.petunin.notify.model.dto.ReminderRequest;
import ru.mtuci.petunin.notify.model.dto.ReminderResponse;
import ru.mtuci.petunin.notify.model.entity.AppUser;
import ru.mtuci.petunin.notify.model.entity.Reminder;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;
import ru.mtuci.petunin.notify.repository.AppUserRepository;
import ru.mtuci.petunin.notify.repository.ReminderRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReminderServiceTest {
    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void shouldCreateReminder() {
        AppUser user = new AppUser();
        user.setId(1L);
        Reminder saved = new Reminder();
        saved.setId(10L);
        saved.setSubject("Meeting");
        saved.setText("Start at 10");
        saved.setChannel(ReminderChannel.EMAIL);
        saved.setRecipient(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(reminderRepository.save(any(Reminder.class))).thenReturn(saved);
        ReminderRequest request = new ReminderRequest();
        request.setSubject("Meeting");
        request.setText("Start at 10");
        request.setChannel(ReminderChannel.EMAIL);
        request.setRecipientId(1L);
        ReminderResponse result = reminderService.create(request);
        assertEquals(10L, result.getId());
        verify(reminderRepository).save(any(Reminder.class));
    }

    @Test
    void shouldThrowWhenRecipientNotFound() {
        ReminderRequest request = new ReminderRequest();
        request.setRecipientId(55L);
        when(userRepository.findById(55L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> reminderService.create(request));
    }
}
