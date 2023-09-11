package com.example.demo.todo.repositories;

import com.example.demo.todo.models.OrderItems;
import com.example.demo.todo.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends MongoRepository<OrderItems, String> {

    List<OrderItems> findAllByIdIn(List<String> ids);
}
