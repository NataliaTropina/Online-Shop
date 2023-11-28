package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
@ToString(exclude = {"cart", "addresses", "orders"})
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
    @DBRef
    private List<Address> addresses;
    @DBRef
    private List<Order> orders;
}
