package com.group0505team1.service;

import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.RequestRegisterDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.dto.UserDTO;
import com.group0505team1.entity.User;
import com.group0505team1.exception.AuthenticationException;
import com.group0505team1.repository.UserRepositoryInterface;

public class UserService {
    UserRepositoryInterface userRepository;
    UserSecurity userSecurity;

    public UserService(UserRepositoryInterface userRepository, UserSecurity userSecurity) {
        this.userRepository = userRepository;
        this.userSecurity = userSecurity;
    }

    ResponseDTO registerUser(RequestRegisterDTO requestRegisterDTO) {
        String name = requestRegisterDTO.getName();
        String login = requestRegisterDTO.getLogin();
        String password = requestRegisterDTO.getPassword();
        String confirmPassword = requestRegisterDTO.getConfirmPassword();
        User newUser;
        try {
            newUser = userSecurity.register(name, login, password, confirmPassword);
            userRepository.add(newUser);
            return new ResponseDTO<>(200, "User registered successfully", UserDTO.fromUser(newUser));
        }catch (AuthenticationException e){
            return new ResponseDTO<>(400, e.getMessage(), null);
        }
    }

}
