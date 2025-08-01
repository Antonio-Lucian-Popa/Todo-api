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

    List<Todo> findAllByUserEmail(String email);
    List<Todo> findByUserEmailAndCreatedAt(String email, LocalDateTime date);
    Optional<Todo> findByIdAndUserEmail(UUID todoId, String email);
}
