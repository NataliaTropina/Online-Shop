package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.OrdersApi;
import com.example.demo.todo.dto.NewOrderDto;
import com.example.demo.todo.dto.OrderDto;
import com.example.demo.todo.dto.OrdersPage;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController implements OrdersApi {

    private final OrdersService ordersService;

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<OrderDto> createOrder(NewOrderDto newOrder, AuthenticatedUser currentUser) {
        return ResponseEntity
                .status(201)
                .body(ordersService.createOrder(newOrder, currentUser));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrdersPage getAll() {
        return ordersService.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderDto getById(String id) {
        return ordersService.getById(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<OrderDto> updateOrder(String id, NewOrderDto newOrder, AuthenticatedUser currentUser) {
        return ResponseEntity
                .status(201)
                .body(ordersService.updateOrder(id, currentUser, newOrder));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<OrderDto> deleteOrderById(String id) {
        return ResponseEntity.ok(ordersService.deleteById(id));
    }
}
