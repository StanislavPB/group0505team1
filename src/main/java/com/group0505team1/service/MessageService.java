package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.MessageDTO;
import com.group0505team1.dto.RequestMessageDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.entity.Message;
import com.group0505team1.entity.User;
import com.group0505team1.repository.MessageRepositoryInterface;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {
    private final MessageRepositoryInterface messageRepositoryInterface;
    private final UserSecurity userSecurity;
    private final UserService userService;

    public MessageService(MessageRepositoryInterface messageRepositoryInterface, UserSecurity userSecurity, UserService userService) {
        this.messageRepositoryInterface = messageRepositoryInterface;
        this.userSecurity = userSecurity;
        this.userService = userService;
    }

    public ResponseDTO sendMessage(RequestMessageDTO requestMessageDTO) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User sender = SessionContext.getCurrentUser();
        ResponseDTO receiver = userService.getUserById(requestMessageDTO.getUserIdReceiver());

        if (requestMessageDTO.getMessage() == null || requestMessageDTO.getMessage().isBlank()) {
            return new ResponseDTO<>(400, "Message cannot be empty!", null);
        }

        if (receiver == null) {
            return new ResponseDTO<>(404, "User not found", null);

        }

        messageRepositoryInterface.sendMessage(sender, (User) receiver.getDataObject(), requestMessageDTO.getMessage());
        return new ResponseDTO(200, "Message sent successfully!", requestMessageDTO.getMessage());
    }

    public ResponseDTO  getReceivedMessages() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User user = SessionContext.getCurrentUser();
        List<Message> incomingMessages = messageRepositoryInterface.getReceivedMessages(user);

        return new ResponseDTO<>(200, "Messages found", MessageDTO.fromMessageList(incomingMessages));
    }

    public ResponseDTO getSentMessages() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        User user = SessionContext.getCurrentUser();
        List<Message> sentMessages = messageRepositoryInterface.getSentMessages(user);

        return new ResponseDTO<>(200, "Messages found", MessageDTO.fromMessageList(sentMessages));

    }

    public ResponseDTO getAllMessages() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO<>(403, "Access denied. Admin rights are required", null);
        }
        List<Message> messages = messageRepositoryInterface.getAllMessages();

       return new ResponseDTO<>(200, "Messages found", MessageDTO.fromMessageList(messages));
    }

}
