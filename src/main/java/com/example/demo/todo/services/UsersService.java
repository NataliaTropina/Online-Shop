package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.ProfileDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.dto.UsersPage;

public interface UsersService {

    ProfileDto getProfile(String currentUserId);

    UsersPage getAll();

    UserDto deleteUserById(String userId);

    UserDto getUserById(String userId);

    UserDto updateUserById(String userId, NewUserDto newUserDto);
}
