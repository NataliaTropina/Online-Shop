package com.example.demo.todo.dto;

import com.example.demo.todo.models.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrderItemsDto {

    private List<OrderItems> orderItems;
    private int quantity;
}
