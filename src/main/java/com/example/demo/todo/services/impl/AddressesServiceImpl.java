package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.AddressDto;
import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.AddressRepository;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.AddressesService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AddressesServiceImpl implements AddressesService {

    private final AddressRepository addressRepository;

    private final UsersRepository usersRepository;
    @Override
    public AddressDto createAddress(NewAddressDto newAddress, AuthenticatedUser currentUser) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                        );

        Address address = Address.builder()
                .street(newAddress.getStreet())
                .houseNumber(newAddress.getHouseNumber())
                .city(newAddress.getCity())
                .Country(newAddress.getCountry())
                .postcode(newAddress.getPostcode())
                .user(user)
                .build();

        addressRepository.save(address);

        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);

        return AddressDto.from(address);
    }
}
