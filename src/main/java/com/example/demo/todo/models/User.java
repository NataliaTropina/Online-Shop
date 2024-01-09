package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getHashPassword(), user.getHashPassword()) && Objects.equals(getCreatedDate(), user.getCreatedDate()) && Objects.equals(getUpdatedDate(), user.getUpdatedDate()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhone(), user.getPhone()) && getRole() == user.getRole() && Objects.equals(getCart(), user.getCart()) && Objects.equals(getAddresses(), user.getAddresses()) && Objects.equals(getOrders(), user.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHashPassword(), getCreatedDate(), getUpdatedDate(), getFirstName(), getLastName(), getEmail(), getPhone(), getRole(), getCart(), getAddresses(), getOrders());
    }
}
