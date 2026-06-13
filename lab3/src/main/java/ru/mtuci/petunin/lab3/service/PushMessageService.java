package ru.mtuci.petunin.lab3.service;

import org.springframework.stereotype.Service;

@Service("push")
public class PushMessageService implements MessageService {
    @Override
    public String channel() {
        return "push";
    }

    @Override
    public void send(String text, String recipient) {
        System.out.println("PUSH: " + recipient + " -> " + text);
    }
}
