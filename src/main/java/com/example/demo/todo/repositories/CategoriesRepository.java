package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends MongoRepository<Category,String> {
}
