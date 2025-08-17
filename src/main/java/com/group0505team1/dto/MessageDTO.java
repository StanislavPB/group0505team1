package com.group0505team1.dto;

import com.group0505team1.entity.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDTO {
    private int id;
    private String message;
    private int userIdSender;
    private int userIdReceiver;
    private LocalDateTime dateTime;

    public MessageDTO(int id, String message, int userIdSender, int userIdReceiver, LocalDateTime dateTime) {
        this.id = id;
        this.message = message;
        this.userIdSender = userIdSender;
        this.userIdReceiver = userIdReceiver;
        this.dateTime = dateTime;
    }
    public static MessageDTO fromMessage(Message message){
        return new MessageDTO(message.getId(), message.getMessage(), message.getUserIdSender(), message.getUserIdReceiver(), message.getDateTime());
    }

    public static List<MessageDTO> fromMessageList(List<Message> messages){
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (Message m : messages){
            messageDTOs.add(fromMessage(m));
        }
        return messageDTOs;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getUserIdSender() {return userIdSender;}

    public int getUserIdReceiver() {
        return userIdReceiver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userIdSender=" + userIdSender +
                ", userIdReceiver=" + userIdReceiver +
                ", dateTime=" + dateTime +
                '}';
    }
}
