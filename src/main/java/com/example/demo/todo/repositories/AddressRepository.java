package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> getAllByUser(User user);
}
