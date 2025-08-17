package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.dto.ProjectDTO;
import com.group0505team1.dto.RequestProjectDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepositoryInterface;

import java.util.List;

public interface ProjectServiceInterface {
    ResponseDTO addProject(RequestProjectDTO requestProjectDTO);
    ResponseDTO findById(int id);
    ResponseDTO findByName(String name);
    ResponseDTO getAllProjects();
    ResponseDTO getMyProjects();
    ResponseDTO addUserToProject(int projectId, int userId);
    ResponseDTO addTaskToProject(int projectId, int taskId);
    ResponseDTO getUsersByProject(int projectId);
    ResponseDTO getTaskByProject(int projectId);
}
