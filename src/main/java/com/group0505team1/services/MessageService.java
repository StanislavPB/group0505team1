package com.group0505team1.services;

import com.group0505team1.entity.Message;
import com.group0505team1.entity.User;
import com.group0505team1.repository.MessageRepository;
import com.group0505team1.repository.UserRepository;

import java.util.List;

public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public void sendMessage(int senderId, int receiverId, String messageText){
        User sender = userRepository.findById(senderId);
        User receiver = userRepository.findById(receiverId);

        if (messageText.isBlank()) {
            throw new IllegalArgumentException("Сообщение не может быть пустым");
        }

        messageRepository.sendMessage(senderId,receiverId, messageText);
    }

    public List<Message> getReceivedMessage(int userId){
        userRepository.findById(userId);
        return messageRepository.getReceivedMessages(userId);
    }

    public List<Message> getSentMessage(int userId){
        userRepository.findById(userId);
        return messageRepository.getSentMessages(userId);
    }
}
