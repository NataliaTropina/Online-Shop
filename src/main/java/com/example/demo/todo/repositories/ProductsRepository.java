package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductsRepository extends MongoRepository<Product, String> {
    List<Product> findAllByIdIn(List<String> ids);
}
