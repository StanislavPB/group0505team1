package com.group0505team1.repository;

import com.group0505team1.entity.Message;
import com.group0505team1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository implements MessageRepositoryInterface {
    private final List<Message> messages = new ArrayList<>();

    @Override
    public void sendMessage(User sender, User receiver, String text) {
        Message message = new Message(text, sender.getId(), receiver.getId());
        messages.add(message);
    }

    @Override
    public List<Message> getReceivedMessages(User user) {
        List<Message> receivedMessages = new ArrayList<>();
        int userId = user.getId();
        for (Message message : messages) {
            if (message.getUserIdReceiver() == userId) {
                receivedMessages.add(message);
            }
        }
        return receivedMessages;
    }

    @Override
    public List<Message> getSentMessages(User user) {
        List<Message> sentMessages = new ArrayList<>();
        int userId = user.getId();
        for (Message message : messages) {
            if (message.getUserIdSender() == userId) {
                sentMessages.add(message);
            }
        }
        return sentMessages;
    }

    @Override
    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }
}
