package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.AddressesApi;
import com.example.demo.todo.dto.AddressDto;
import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.AddressesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressesController implements AddressesApi {

    private final AddressesService addressService;

    @Override
    public ResponseEntity<AddressDto> createAddress(NewAddressDto newAddress, AuthenticatedUser currentUser) {
        return ResponseEntity.status(201).body(addressService.createAddress(newAddress, currentUser));
    }
}
