package com.example.demo.todo.repositories;

import com.example.demo.todo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsById(String id);

    List<User> findAllByRole(String role);
}
