package com.example.demo.todo.services;

import com.example.demo.todo.dto.CartDto;
import com.example.demo.todo.dto.NewCartDto;
import com.example.demo.todo.security.datails.AuthenticatedUser;

public interface CartsService {

    CartDto addToCart(AuthenticatedUser currentUser, NewCartDto newCart);
}
