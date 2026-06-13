package ru.mtuci.petunin.notify.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;

@Getter
@Setter
public class ReminderRequest {
    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    @NotNull
    private ReminderChannel channel;

    @NotNull
    private Long recipientId;
}
