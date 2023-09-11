package com.example.demo.todo.dto;

import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.OrderItems;
import com.example.demo.todo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String id;
    private String userId;
    private List<String> orderItemsIds;
    private LocalDate orderDate;
    private Order.Status status;

    public static OrderDto from (Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderItemsIds(order.getOrderItems().stream().map(OrderItems::getId).collect(Collectors.toList()))
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }

    public static List<OrderDto> from (List<Order> orders){

        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
