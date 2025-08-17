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

public interface UserServiceInterface {

    ResponseDTO registerUser(RequestRegisterDTO requestRegisterDTO);
    ResponseDTO authenticate(RequestAuthDTO requestAuthDTO);
    ResponseDTO logout();
    ResponseDTO getUserById(int id);
    ResponseDTO getUserByLogin(String login);
    ResponseDTO getUsersByName(String name);
    ResponseDTO getAllUsers();
    ResponseDTO assignTaskToUser(int idTask, int idUser);
    ResponseDTO getTasksByUserId(int idUser);
    ResponseDTO getMyTasks();
    ResponseDTO setUserRole(int idUser, String role);
    ResponseDTO getAllUserFromTaskId(int idTask);
}
