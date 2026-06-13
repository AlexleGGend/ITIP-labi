package ru.mtuci.petunin.lab3.service;

import org.springframework.stereotype.Service;

@Service("telegram")
public class TelegramMessageService implements MessageService {
    @Override
    public String channel() {
        return "telegram";
    }

    @Override
    public void send(String text, String recipient) {
        System.out.println("TELEGRAM: " + recipient + " -> " + text);
    }
}
