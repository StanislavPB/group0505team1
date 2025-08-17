package com.group0505team1.dto;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.List;

public class RequestProjectDTO {
    private String title;
    private String description;

    public RequestProjectDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
