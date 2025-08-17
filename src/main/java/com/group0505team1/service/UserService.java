package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.*;
import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.exception.AuthenticationException;
import com.group0505team1.repository.UserRepositoryInterface;

import java.util.Arrays;
import java.util.List;

public class UserService implements UserServiceInterface{
    private final UserRepositoryInterface userRepository;
    private final TaskServiceInterface taskService;
    private final UserSecurity userSecurity;

    public UserService(UserRepositoryInterface userRepository, TaskServiceInterface taskService, UserSecurity userSecurity) {
        this.userRepository = userRepository;
        this.taskService =  taskService;
        this.userSecurity = userSecurity;
    }

    @Override
    public ResponseDTO registerUser(RequestRegisterDTO requestRegisterDTO) {
        String name = requestRegisterDTO.getName();
        String login = requestRegisterDTO.getLogin();
        String password = requestRegisterDTO.getPassword();
        String confirmPassword = requestRegisterDTO.getConfirmPassword();
        User newUser;
        try {
            newUser = userSecurity.register(name, login, password, confirmPassword);
            userRepository.add(newUser);
            return new ResponseDTO<>(200, "User registered successfully", UserDTO.fromUser(newUser));
        } catch (AuthenticationException e) {
            return new ResponseDTO<>(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO authenticate(RequestAuthDTO requestAuthDTO) {
        try {
            User user = userSecurity.authenticate(requestAuthDTO.getLogin(), requestAuthDTO.getPassword());
            return new ResponseDTO<>(200, "User authenticated successfully", UserDTO.fromUser(user));
        } catch (AuthenticationException e) {
            return new ResponseDTO<>(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO logout() {
        try {
            userSecurity.logout();
            return new ResponseDTO<>(200, "User logged out successfully", null);
        } catch (AuthenticationException e) {
            return new ResponseDTO<>(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO getUserById(int id) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User user = userRepository.findById(id);
        if (user == null) {
            return new ResponseDTO(404, "User not found", null);
        }
        return new ResponseDTO(200, "User found", UserDTO.fromUser(user));
    }

    @Override
    public ResponseDTO getUserByLogin(String login) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User user = userRepository.findByLogin(login);
        if (user == null) {
            return new ResponseDTO<>(404, "User not found", null);
        }
        return new ResponseDTO<>(200, "User found", UserDTO.fromUser(user));
    }

    @Override
    public ResponseDTO getUsersByName(String name) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        List<User> users = userRepository.findByName(name);
        if (users.isEmpty()) {
            return new ResponseDTO<>(404, "Users not found", null);
        }
        return new ResponseDTO<>(200, "Users found", UserDTO.fromUserList(users));
    }

    @Override
    public ResponseDTO getAllUsers() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseDTO<>(404, "Users not found", null);
        }
        return new ResponseDTO<>(200, "Users found", UserDTO.fromUserList(users));
    }

    @Override
    public ResponseDTO assignTaskToUser(int idTask, int idUser) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO<>(403, "Access denied. Admin rights are required", null);
        }
        User user = userRepository.findById(idUser);
        if (user == null) {
            return new ResponseDTO<>(404, "User not found", null);
        }
        ResponseDTO responseDTO = taskService.findTaskById(idTask);
        if (responseDTO.getCode() != 200) {
            return new ResponseDTO<>(404, "Task not found", null);
        }
        user.addTask((Task) responseDTO.getDataObject());
        return new ResponseDTO<>(200, "Task assigned successfully", null);
    }

    @Override
    public ResponseDTO getTasksByUserId(int idUser) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO<>(403, "Access denied. Admin rights are required", null);
        }
        User user = userRepository.findById(idUser);
        if (user == null) {
            return new ResponseDTO<>(404, "User not found", null);
        }
        return new ResponseDTO<>(200, "Tasks found", TaskDTO.fromTaskList(user.getUserTasks()));
    }

    @Override
    public ResponseDTO getMyTasks() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User user = SessionContext.getCurrentUser();
        return new ResponseDTO(200, "Tasks found", TaskDTO.fromTaskList(user.getUserTasks()));
    }

    @Override
    public ResponseDTO setUserRole(int idUser, String role) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO<>(403, "Access denied. Admin rights are required", null);
        }
        if (Arrays.stream(RoleUser.values()).noneMatch(r -> r.name().equals(role))) {
            return new ResponseDTO<>(400, "Invalid role", null);
        }
        User user = userRepository.findById(idUser);
        if (user == null) {
            return new ResponseDTO<>(404, "User not found", null);
        }
        user.setRoleUser(RoleUser.valueOf(role));
        return new ResponseDTO<>(200, "Role set successfully", null);
    }
}
