package com.example.demo.todo.repositories;

import com.example.demo.todo.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
