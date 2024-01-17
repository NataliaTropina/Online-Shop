package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.CartsApi;
import com.example.demo.todo.dto.CartDto;
import com.example.demo.todo.dto.NewCartDto;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartsController implements CartsApi {

    private final CartsService cartService;

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CartDto> addToCart(AuthenticatedUser currentUser, NewCartDto newCart) {
        return ResponseEntity.status(201).body(cartService.addToCart(currentUser, newCart));
    }
}
