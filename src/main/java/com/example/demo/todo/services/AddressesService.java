package com.example.demo.todo.services;

import com.example.demo.todo.dto.AddressDto;
import com.example.demo.todo.dto.AddressesPage;
import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.security.datails.AuthenticatedUser;

public interface AddressesService {

    AddressDto createAddress(NewAddressDto newAddress, AuthenticatedUser currentUser);

    AddressDto updateAddress(NewAddressDto newAddress, String id);

    AddressDto deleteAddress(String id, AuthenticatedUser currentUser);

    AddressesPage getAddressesByUser(AuthenticatedUser currentUser);
}
