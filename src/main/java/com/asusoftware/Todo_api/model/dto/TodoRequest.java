package com.asusoftware.Todo_api.model.dto;


import lombok.Data;

@Data
public class TodoRequest {
    private String title;
    private String description;
    private boolean completed;
}