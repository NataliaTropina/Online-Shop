package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "products")
@ToString(exclude = "category")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageURL;
    private String country;
    @DBRef
    private Category category;
    private double price;
    private int quantity;
}
