package com.group0505team1.entity;

import java.time.LocalDateTime;

public class Message {
    private static int counter = 0;
    private int id;
    private int userIdSender;
    private int userIdReceiver;
    private LocalDateTime dateTime;

    public Message( int userIdSender, int userIdReceiver) {
        this.id = ++counter;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userIdSender=" + userIdSender +
                ", userIdReceiver=" + userIdReceiver +
                ", dateTime=" + dateTime +
                '}';
    }
}
