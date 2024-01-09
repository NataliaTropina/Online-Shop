package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Double.compare(product.getPrice(), getPrice()) == 0 && getQuantity() == product.getQuantity() && Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getImageURL(), product.getImageURL()) && Objects.equals(getCountry(), product.getCountry()) && Objects.equals(getCategory(), product.getCategory());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getImageURL(), getCountry(), getCategory(), getPrice(), getQuantity());
    }
}
