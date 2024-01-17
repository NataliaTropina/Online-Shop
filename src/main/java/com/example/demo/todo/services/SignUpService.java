package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.UserDto;

public interface SignUpService {

    UserDto signUp(NewUserDto newUser);
}
