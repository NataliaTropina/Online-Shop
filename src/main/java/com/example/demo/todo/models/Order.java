package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public enum Status{
        PROCESSING, SHIPPED, DELIVERED
    }

    @Id
    private String id;
    private User user;
    private List<OrderItems> orderItems;
    private double totalPrice;
    private LocalDate orderDate;
    private Status status;
    
}
