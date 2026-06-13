package ru.mtuci.petunin.lab3.service;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsMessageService implements MessageService {
    @Override
    public String channel() {
        return "sms";
    }

    @Override
    public void send(String text, String recipient) {
        System.out.println("SMS: " + recipient + " -> " + text);
    }
}
