package com.asusoftware.Todo_api.repository;

import com.asusoftware.Todo_api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findAllByUserId(UUID userId);
    List<Todo> findByUserIdAndCreatedAt(UUID userId, LocalDateTime date);
    Optional<Todo> findByIdAndUserId(UUID todoId, UUID userId);
}
