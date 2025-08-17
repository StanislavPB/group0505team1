package com.group0505team1.entity;

import java.time.LocalDateTime;

public class Message {
    private static int counter = 0;
    private int id;
    private String message;
    private int userIdSender;
    private int userIdReceiver;
    private LocalDateTime dateTime;


    public Message(String message, int userIdSender, int userIdReceiver) {
        this.id = ++counter;
        this.message = message;
        this.userIdSender = userIdSender;
        this.userIdReceiver = userIdReceiver;
        this.dateTime = LocalDateTime.now();
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
