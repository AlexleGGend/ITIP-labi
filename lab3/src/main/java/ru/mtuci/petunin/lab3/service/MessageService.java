package ru.mtuci.petunin.lab3.service;

public interface MessageService {
    String channel();
    void send(String text, String recipient);
}
