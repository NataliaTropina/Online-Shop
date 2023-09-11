package com.example.demo.todo.dto;

import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.OrderItems;
import com.example.demo.todo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemsDto {

    private String id;
    private String productId;
    private int quantity;

    public static OrderItemsDto from (OrderItems orderItems){
        return OrderItemsDto.builder()
                .id(orderItems.getId())
                .productId(orderItems.getId())
                .quantity(orderItems.getQuantity())
                .build();
    }

    public static List<OrderItemsDto> from (List<OrderItems> orderItems) {
        return orderItems.stream().map(OrderItemsDto::from).collect(Collectors.toList());
    }
}
