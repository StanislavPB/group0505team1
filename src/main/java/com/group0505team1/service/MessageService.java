package com.group0505team1.service;

import com.group0505team1.dto.MessageDTO;
import com.group0505team1.entity.Message;
import com.group0505team1.entity.User;
import com.group0505team1.repository.MessageRepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {
   private final MessageRepositoryInterface messageRepositoryInterface;

    public MessageService(MessageRepositoryInterface messageRepositoryInterface) {
        this.messageRepositoryInterface = messageRepositoryInterface;
    }

    public void sendMessage(User sender, User receiver, String text){
        if (text == null || text.isBlank()){
            throw new IllegalArgumentException("Message cannot be empty");
        }
        if (sender == null || receiver == null){
            throw new IllegalArgumentException("Sender and receiver cannot be null");
        }
        messageRepositoryInterface.sendMessage(sender,receiver,text);
    }

    public List<MessageDTO>  getReceivedMessages(User user){
        return messageRepositoryInterface.getReceivedMessages(user).stream()
                .map(m -> MessageDTO.fromMessage(m))
                .collect(Collectors.toList());
    }

    public List<MessageDTO>  getSentMessages(User user){
        return messageRepositoryInterface.getSentMessages(user).stream()
                .map(MessageDTO::fromMessage)
                .collect(Collectors.toList());
    }

    public List<MessageDTO>  getAllMessages(){
        return messageRepositoryInterface.getAllMessages().stream()
                .map(m -> MessageDTO.fromMessage(m))
                .collect(Collectors.toList());
    }

}
