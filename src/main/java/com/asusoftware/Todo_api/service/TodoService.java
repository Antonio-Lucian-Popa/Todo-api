package com.asusoftware.Todo_api.service;

import com.asusoftware.Todo_api.model.Todo;
import com.asusoftware.Todo_api.model.dto.TodoRequest;
import com.asusoftware.Todo_api.model.dto.TodoResponse;
import com.asusoftware.Todo_api.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoResponse> getTodosByUser(UUID userId) {
        return todoRepository.findAllByUserId(userId).stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TodoResponse> getTodosByDate(UUID userId, LocalDateTime date) {
        return todoRepository.findByUserIdAndCreatedAt(userId, date).stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public TodoResponse getTodoById(UUID userId, UUID todoId) {
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        return TodoResponse.fromEntity(todo);
    }

    public TodoResponse createTodo(UUID userId, TodoRequest request) {
        Todo todo = Todo.builder()
                .userId(userId)
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        return TodoResponse.fromEntity(todoRepository.save(todo));
    }

    public TodoResponse updateTodo(UUID userId, UUID todoId, TodoRequest request) {
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());

        return TodoResponse.fromEntity(todoRepository.save(todo));
    }

    public void deleteTodo(UUID userId, UUID todoId) {
        Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }
}
