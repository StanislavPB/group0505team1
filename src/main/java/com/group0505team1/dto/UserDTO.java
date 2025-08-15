package com.group0505team1.dto;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String login;
    private RoleUser roleUser;
    private List<Task> userTasks;

    public UserDTO(int id, String name, String login, RoleUser roleUser, List<Task> userTasks) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.roleUser = roleUser;
        this.userTasks = userTasks;
    }

    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getName(), user.getLogin(), user.getRoleUser(), user.getUserTasks());
    }

    public static List<UserDTO> fromUserList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users){
            userDTOs.add(fromUser(user));
        }
        return userDTOs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }
}
