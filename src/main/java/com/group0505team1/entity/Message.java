package com.group0505team1.entity;

import java.time.LocalDateTime;

public class Message {
    private static int counter = 0;
    private int id;
    private String message;
    private int userIdSender;
    private int userIdReceiver;
    private LocalDateTime dateTime;


    public Message(int id, String message, int userIdSender, int userIdReceiver, LocalDateTime dateTime) {
        this.id = id;
        this.message = message;
        this.userIdSender = userIdSender;
        this.userIdReceiver = userIdReceiver;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
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

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userIdSender=" + userIdSender +
                ", userIdReceiver=" + userIdReceiver +
                ", dateTime=" + dateTime +
                '}';
    }
}
