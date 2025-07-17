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

    public List<TodoResponse> getTodosByUser(String email) {
        return todoRepository.findAllByUserEmail(email).stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<TodoResponse> getTodosByDate(String email, LocalDateTime date) {
        return todoRepository.findByUserEmailAndCreatedAt(email, date).stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public TodoResponse getTodoById(String email, UUID todoId) {
        Todo todo = todoRepository.findByIdAndUserEmail(todoId, email)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        return TodoResponse.fromEntity(todo);
    }

    public TodoResponse createTodo(String email, TodoRequest request) {
        Todo todo = Todo.builder()
                .userEmail(email)
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        return TodoResponse.fromEntity(todoRepository.save(todo));
    }

    public TodoResponse updateTodo(String email, UUID todoId, TodoRequest request) {
        Todo todo = todoRepository.findByIdAndUserEmail(todoId, email)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if(request.getTitle() != null && !request.getTitle().isEmpty()) {
            todo.setTitle(request.getTitle());
        }

        if(request.getDescription() != null && !request.getDescription().isEmpty()) {
            todo.setDescription(request.getDescription());
        }

        todo.setCompleted(request.isCompleted());

        return TodoResponse.fromEntity(todoRepository.save(todo));
    }

    public void deleteTodo(String email, UUID todoId) {
        Todo todo = todoRepository.findByIdAndUserEmail(todoId, email)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }
}
