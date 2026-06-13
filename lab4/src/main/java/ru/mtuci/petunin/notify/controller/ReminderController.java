package ru.mtuci.petunin.notify.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mtuci.petunin.notify.model.dto.ReminderRequest;
import ru.mtuci.petunin.notify.model.dto.ReminderResponse;
import ru.mtuci.petunin.notify.model.enums.ReminderChannel;
import ru.mtuci.petunin.notify.model.enums.ReminderStatus;
import ru.mtuci.petunin.notify.service.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ReminderResponse create(@RequestBody @Valid ReminderRequest request) {
        return reminderService.create(request);
    }

    @GetMapping
    public List<ReminderResponse> findAll() {
        return reminderService.findAll();
    }

    @GetMapping("/status")
    public List<ReminderResponse> findByStatus(@RequestParam ReminderStatus status) {
        return reminderService.findByStatus(status);
    }

    @GetMapping("/channel")
    public List<ReminderResponse> findByChannel(@RequestParam ReminderChannel channel) {
        return reminderService.findByChannel(channel);
    }

    @PutMapping("/{id}/sent")
    public ReminderResponse markSent(@PathVariable Long id) {
        return reminderService.markSent(id);
    }
}
