package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Double.compare(order.getTotalPrice(), getTotalPrice()) == 0 && Objects.equals(getId(), order.getId()) && Objects.equals(getUser(), order.getUser()) && Objects.equals(getCartDetails(), order.getCartDetails()) && Objects.equals(getOrderDate(), order.getOrderDate()) && getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getCartDetails(), getTotalPrice(), getOrderDate(), getStatus());
    }
}
