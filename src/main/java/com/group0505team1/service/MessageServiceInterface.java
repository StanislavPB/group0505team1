package com.group0505team1.service;

import com.group0505team1.dto.RequestMessageDTO;
import com.group0505team1.dto.ResponseDTO;

public interface MessageServiceInterface {
    ResponseDTO sendMessage(RequestMessageDTO requestMessageDTO);
    ResponseDTO getReceivedMessages();
    ResponseDTO getSentMessages();
    ResponseDTO getAllMessages();
}
