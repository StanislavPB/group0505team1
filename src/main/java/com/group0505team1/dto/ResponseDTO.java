package com.group0505team1.dto;

public class ResponseDTO <T> {
    Integer code;
    String message;
    T dataObject;

    public ResponseDTO(Integer code, String message, T dataObject) {
        this.code = code;
        this.message = message;
        this.dataObject = dataObject;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getDataObject() {
        return dataObject;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
