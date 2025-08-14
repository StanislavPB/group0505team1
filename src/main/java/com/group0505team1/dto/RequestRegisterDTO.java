package com.group0505team1.dto;

public class RequestRegisterDTO {
    String name;
    String login;
    String password;
    String confirmPassword;

    public RequestRegisterDTO(String name, String login, String password, String confirmPassword) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
