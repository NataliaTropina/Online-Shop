package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Product, String> {

}
