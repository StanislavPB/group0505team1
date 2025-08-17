package com.group0505team1.service;

import com.group0505team1.dto.RequestTaskDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.dto.TaskDTO;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskPriority;

public interface TaskServiceInterface {
    ResponseDTO addTask(RequestTaskDTO requestTaskDTO);
    ResponseDTO findAllTasks();
    ResponseDTO findTaskById(int taskId);
    ResponseDTO findTaskByFilter(Boolean active, TaskPriority priority);
    ResponseDTO setTaskStatus(int taskId, String taskStatus);
    ResponseDTO setTaskPriority(int taskId, String taskPriority);
    ResponseDTO setDeadline(int taskId, String deadline);
    ResponseDTO assignTaskToProject(int taskId, int projectId);
    void setProjectService(ProjectServiceInterface projectService);
    Task of(TaskDTO taskDTO);
}

