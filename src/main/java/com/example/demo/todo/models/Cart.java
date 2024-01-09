package com.example.demo.todo.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "carts")
@ToString(exclude = "user")
public class Cart {

    @Id
    private String id;
    private List<CartDetails> cartDetails;
    @DBRef
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart cart)) return false;
        return Objects.equals(getId(), cart.getId()) && Objects.equals(getCartDetails(), cart.getCartDetails()) && Objects.equals(getUser(), cart.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCartDetails(), getUser());
    }
}
