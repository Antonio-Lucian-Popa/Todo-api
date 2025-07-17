package com.asusoftware.Todo_api.model.dto;

import com.asusoftware.Todo_api.model.Todo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TodoResponse {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    public static TodoResponse fromEntity(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}