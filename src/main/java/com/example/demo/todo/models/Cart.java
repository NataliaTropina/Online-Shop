package com.example.demo.todo.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


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
}
