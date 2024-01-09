package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getId(), category.getId()) && Objects.equals(getName(), category.getName()) && Objects.equals(getDescription(), category.getDescription()) && Objects.equals(getProducts(), category.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getProducts());
    }
}
