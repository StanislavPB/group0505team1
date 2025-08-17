package com.group0505team1.dto;

public class RegistrationResult {
    private boolean success;
    private String message;

    public RegistrationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RegistrationResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
