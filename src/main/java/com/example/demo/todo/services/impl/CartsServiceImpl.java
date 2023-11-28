package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.CartDto;
import com.example.demo.todo.dto.NewCartDetailsDto;
import com.example.demo.todo.dto.NewCartDto;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.CartDetails;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.CartRepository;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.CartsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartsServiceImpl implements CartsService {

    private final UsersRepository usersRepository;

    private final CartRepository cartRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public CartDto addToCart(AuthenticatedUser currentUser, NewCartDto newCart) {
        User user = usersRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() ->
                        new NotFoundException("User with id <" + currentUser.getUser().getId() + "> not found")
                );

        List<CartDetails> cartDetails = newCart.getCartDetails().stream()
                .map(newCartDetailsDto -> modelMapper.map(newCartDetailsDto, CartDetails.class))
                .collect(Collectors.toList());

        Cart cart = Cart.builder()
                .cartDetails(cartDetails)
                .user(user)
                .build();

        cartRepository.save(cart);
        user.setCart(cart);
        usersRepository.save(user);

        return CartDto.from(cart);
    }


}
