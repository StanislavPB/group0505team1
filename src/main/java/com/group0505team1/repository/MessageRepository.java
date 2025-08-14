package com.group0505team1.repository;

import com.group0505team1.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements MessageRepositoryInterface {
    private final List<Message> messages = new ArrayList<>();

    @Override
    public void sendMessage(int userIdSender, int userIdReceiver, String text) {
        Message message = new Message(text, userIdSender, userIdReceiver);
        messages.add(message);
    }

    @Override
    public List<Message> getReceivedMessages(int idUser) {
        return List.of();
    }

    @Override
    public List<Message> getSentMessages(int idUser) {
        return List.of();
    }

    @Override
    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }
}
