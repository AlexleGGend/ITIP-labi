package ru.mtuci.petunin.notify.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;
import ru.mtuci.petunin.notify.model.enums.ReminderStatus;

@Getter
@AllArgsConstructor
public class ReminderResponse {
    private Long id;
    private String subject;
    private String text;
    private ReminderChannel channel;
    private ReminderStatus status;
    private Long recipientId;
}
