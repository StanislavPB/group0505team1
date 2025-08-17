package com.group0505team1.repository;

import com.group0505team1.entity.Message;
import com.group0505team1.entity.User;

import java.util.List;

public interface MessageRepositoryInterface {
    void sendMessage(User sender, User receiver, String message);

    List<Message> getReceivedMessages(User user);

    List<Message> getSentMessages(User user);

    List<Message> getAllMessages();
}
