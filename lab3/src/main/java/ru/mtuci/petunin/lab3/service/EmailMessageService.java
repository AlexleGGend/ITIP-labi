package ru.mtuci.petunin.lab3.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("email")
public class EmailMessageService implements MessageService {
    @Override
    public String channel() {
        return "email";
    }

    @Override
    public void send(String text, String recipient) {
        System.out.println("EMAIL: " + recipient + " -> " + text);
    }
}
