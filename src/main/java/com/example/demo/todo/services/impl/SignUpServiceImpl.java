package com.example.demo.todo.services.impl;


import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.CartRepository;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    @Override
    public UserDto signUp(NewUserDto newUser) {
        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .phone(newUser.getPhone())
                .hashPassword(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();

        Cart cart = new Cart();
        cartRepository.save(cart);

        user.setCart(cart);

        usersRepository.save(user);

        return UserDto.from(user);
    }
}
