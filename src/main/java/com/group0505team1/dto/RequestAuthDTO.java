package com.group0505team1.dto;

public class RequestAuthDTO {
    String login;
    String password;

    public RequestAuthDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
