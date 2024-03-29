package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewOrderDto;
import com.example.demo.todo.dto.OrderDto;
import com.example.demo.todo.dto.OrdersPage;
import com.example.demo.todo.security.datails.AuthenticatedUser;

public interface OrdersService {

    OrderDto createOrder(AuthenticatedUser currentUser);

    OrdersPage getAll();

    OrderDto getById(String id);

    OrderDto updateOrder(String id, AuthenticatedUser currentUser, NewOrderDto newOrder);

    OrderDto deleteById(String id);
}
