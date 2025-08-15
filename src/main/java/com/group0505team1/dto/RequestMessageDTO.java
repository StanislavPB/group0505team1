package com.group0505team1.dto;

import java.time.LocalDateTime;

public class RequestMessageDTO {

    private String message;
    private int userIdReceiver;

    public RequestMessageDTO(String message, int userIdReceiver) {
        this.message = message;
        this.userIdReceiver = userIdReceiver;
    }

    public String getMessage() {
        return message;
    }

    public int getUserIdReceiver() {
        return userIdReceiver;
    }
}
