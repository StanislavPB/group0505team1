package com.group0505team1.service;

import com.group0505team1.dto.ResponseDTO;

public interface StatisticServiceInterface {
    ResponseDTO getProjectsTotalQuantity();
    ResponseDTO getProjectsDoneQuantity();
    ResponseDTO getProjectsInProcessQuantity();

    ResponseDTO getTaskTotalQuantity();
    ResponseDTO getTaskDoneQuantity();
    ResponseDTO getTaskInProcessQuantity();
    ResponseDTO getTaskOverTimeQuantity();

    ResponseDTO getUserTotalQuantity();
    ResponseDTO getUsersByTasksQuantity();
}
