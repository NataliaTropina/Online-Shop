package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {
}
