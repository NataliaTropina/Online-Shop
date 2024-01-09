package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.AddressDto;
import com.example.demo.todo.dto.AddressesPage;
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

        user.getAddresses().add(address);

        addressRepository.save(address);
        usersRepository.save(user);

        return AddressDto.from(address);
    }

    @Override
    public AddressDto updateAddress (NewAddressDto newAddress, String id) {

       Address addressById = addressRepository.findById(id)
                       .orElseThrow(()->
                               new NotFoundException("Address with ID < + currentUser.getUser().getId() + not found"));

       addressById.setCity(newAddress.getCity());
       addressById.setCountry(newAddress.getCountry());
       addressById.setStreet(newAddress.getStreet());
       addressById.setPostcode(newAddress.getPostcode());
       addressById.setHouseNumber(newAddress.getHouseNumber());

       addressRepository.save(addressById);

        return AddressDto.from(addressById);
    }

    @Override
    public AddressDto deleteAddress(String id, AuthenticatedUser currentUser) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found"));

        Address addressById = addressRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Address with ID <" + id + "not found"));

        addressRepository.deleteById(id);

        return AddressDto.from(addressById);
    }

    @Override
    public AddressesPage getAddressesByUser(AuthenticatedUser currentUser) {

        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(()->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found"));

        List<Address> addressesByUser = addressRepository.getAllByUser(user);

        return AddressesPage.builder()
                .data(AddressDto.from(addressesByUser))
                .build();
    }
}
