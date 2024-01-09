package com.example.demo.todo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "cart_details")
public class CartDetails {
    @Id
    private String id;
    private String productId;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDetails that)) return false;
        return getQuantity() == that.getQuantity() && Objects.equals(getId(), that.getId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId(), getQuantity());
    }
}
