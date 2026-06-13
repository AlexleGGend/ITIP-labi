package ru.mtuci.petunin.lab3.controller;

import ru.mtuci.petunin.lab3.service.NotificationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {
    private final NotificationManager manager;

    public NotificationController(NotificationManager manager) {
        this.manager = manager;
    }

    @GetMapping("/default")
    public String sendDefault(@RequestParam String text, @RequestParam String recipient) {
        manager.sendDefault(text, recipient);
        return "Отправлено через основной канал";
    }

    @GetMapping("/all")
    public String sendAll(@RequestParam String text, @RequestParam String recipient) {
        manager.sendAll(text, recipient);
        return "Отправлено через все каналы";
    }

    @GetMapping("/{beanName}")
    public String sendByName(@PathVariable String beanName,
                             @RequestParam String text,
                             @RequestParam String recipient) {
        manager.sendByBeanName(beanName, text, recipient);
        return "Отправлено через " + beanName;
    }
}
