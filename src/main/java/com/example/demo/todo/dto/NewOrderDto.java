package com.example.demo.todo.dto;

import com.example.demo.todo.models.CartDetails;
import com.example.demo.todo.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderDto {

    private LocalDate orderDate;
    private Order.Status status;
    private List<CartDetails> cartDetails;
}
