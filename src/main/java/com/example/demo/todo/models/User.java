package com.example.demo.todo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    public enum Role {
        USER, ADMIN
    }

    @Id
    private String id;
    private String hashPassword;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
    @DBRef
    private Cart cart = new Cart();
    private Address address;
}
