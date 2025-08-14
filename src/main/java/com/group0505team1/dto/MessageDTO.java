package com.group0505team1.dto;

import com.group0505team1.entity.Message;

import java.time.LocalDateTime;

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

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getUserIdSender() {
        return userIdSender;
    }

    public int getUserIdReceiver() {
        return userIdReceiver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
