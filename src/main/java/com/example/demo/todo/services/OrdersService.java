package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.dto.NewOrderDto;
import com.example.demo.todo.dto.OrderDto;
import com.example.demo.todo.dto.OrdersPage;
import com.example.demo.todo.security.datails.AuthenticatedUser;

import java.util.List;

public interface OrdersService {

    OrderDto createOrder (AuthenticatedUser currentUser);

    OrdersPage getAll ();

    OrderDto getById(String id);

    OrderDto updateOrder (String id, AuthenticatedUser currentUser);

    OrderDto deleteById (String id);
}
