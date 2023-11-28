package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "orders")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class Order {

    public enum Status{
        PROCESSING, SHIPPED, DELIVERED
    }

    @Id
    private String id;
    @DBRef
    private User user;
    private List<CartDetails> cartDetails;
    private double totalPrice;
    private LocalDate orderDate;
    private Status status;
    
}
