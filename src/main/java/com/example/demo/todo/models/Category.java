package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "products")
public class Category {

    @Id
    private String id;
    private String name;
    private String description;
    @DBRef
    private List<Product> products;

}
