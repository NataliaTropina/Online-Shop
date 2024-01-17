package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.AddressRepository;
import com.example.demo.todo.repositories.CartRepository;
import com.example.demo.todo.repositories.OrdersRepository;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final OrdersRepository ordersRepository;


    @Override
    public UserDto signUp(NewUserDto newUser) {
        User user = User.builder().firstName(newUser.getFirstName()).lastName(newUser.getLastName()).email(newUser.getEmail()).phone(newUser.getPhone()).hashPassword(passwordEncoder.encode(newUser.getPassword())).role(User.Role.USER).build();

        usersRepository.save(user);

        Address emptyAddress = new Address();
        addressRepository.save(emptyAddress);

        Order emptyOrder = new Order();
        ordersRepository.save(emptyOrder);

        Cart emptyCart = new Cart();
        cartRepository.save(emptyCart);

        List<Address> addresses = Collections.singletonList(emptyAddress);
        user.setAddresses(addresses);

        List<Order> orders = Collections.singletonList(emptyOrder);
        user.setOrders(orders);

        user.setCart(emptyCart);
        usersRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAddressIds(addresses.stream().map(Address::getId).collect(Collectors.toList()));
        userDto.setOrderIds(orders.stream().map(Order::getId).collect(Collectors.toList()));
        userDto.setCartId(emptyCart.getId());

        return UserDto.from(user);
    }

}
