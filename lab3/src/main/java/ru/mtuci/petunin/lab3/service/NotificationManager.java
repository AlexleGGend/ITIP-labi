package ru.mtuci.petunin.lab3.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotificationManager {
    private final MessageService defaultService;
    private final List<MessageService> services;
    private final Map<String, MessageService> serviceMap;

    public NotificationManager(@Qualifier("email") MessageService defaultService,
                               List<MessageService> services,
                               Map<String, MessageService> serviceMap) {
        this.defaultService = defaultService;
        this.services = services;
        this.serviceMap = serviceMap;
    }

    public void sendDefault(String text, String recipient) {
        defaultService.send(text, recipient);
    }

    public void sendAll(String text, String recipient) {
        services.forEach(service -> service.send(text, recipient));
    }

    public void sendByBeanName(String beanName, String text, String recipient) {
        MessageService service = serviceMap.get(beanName);
        if (service == null) {
            throw new IllegalArgumentException("Канал не найден");
        }
        service.send(text, recipient);
    }
}
