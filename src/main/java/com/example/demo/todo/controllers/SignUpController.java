package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.SignUpApi;
import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {
    private final SignUpService signUpService;

    @Override
    public ResponseEntity<UserDto> signUp(NewUserDto newUser) {
        return ResponseEntity.status(201).body(signUpService.signUp(newUser));
    }
}
