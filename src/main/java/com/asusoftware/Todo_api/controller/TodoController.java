package com.asusoftware.Todo_api.controller;

import com.asusoftware.Todo_api.model.dto.TodoRequest;
import com.asusoftware.Todo_api.model.dto.TodoResponse;
import com.asusoftware.Todo_api.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    private UUID extractUserId(Principal principal) {
        return UUID.fromString(principal.getName());
    }

    @GetMapping
    public List<TodoResponse> getAllTodos(Principal principal) {
        return todoService.getTodosByUser(extractUserId(principal));
    }

    @GetMapping("/filter")
    public List<TodoResponse> getTodosByDate(@RequestParam LocalDateTime date, Principal principal) {
        return todoService.getTodosByDate(extractUserId(principal), date);
    }

    @GetMapping("/{id}")
    public TodoResponse getTodoById(@PathVariable UUID id, Principal principal) {
        return todoService.getTodoById(extractUserId(principal), id);
    }

    @PostMapping
    public TodoResponse createTodo(@RequestBody TodoRequest request, Principal principal) {
        return todoService.createTodo(extractUserId(principal), request);
    }

    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable UUID id,
                                   @RequestBody TodoRequest request,
                                   Principal principal) {
        return todoService.updateTodo(extractUserId(principal), id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable UUID id, Principal principal) {
        todoService.deleteTodo(extractUserId(principal), id);
    }
}
