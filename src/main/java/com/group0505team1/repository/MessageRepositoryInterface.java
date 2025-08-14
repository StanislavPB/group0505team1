package com.group0505team1.repository;

import com.group0505team1.entity.Message;

import java.util.List;

public interface MessageRepositoryInterface {
    void sendMessage(int userIdSender, int userIdReceiver, String message);
    List<Message> getReceivedMessages(int idUser);
    List<Message> getSentMessages(int idUser);
    List<Message> getAllMessages();
}
