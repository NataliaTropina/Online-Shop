package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.UsersApi;
import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.ProfileDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.dto.UsersPage;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import com.example.demo.todo.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService usersService;


    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<ProfileDto> getProfile(AuthenticatedUser currentUser) {
        String currentUserId = currentUser.getUser().getId();
        ProfileDto profile = usersService.getProfile(currentUserId);

        return ResponseEntity.ok(profile);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UsersPage> getAll(String role) {
        return ResponseEntity
                .ok(usersService.getAll(role));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> deleteUserById(String userId) {
        return ResponseEntity.ok(usersService.deleteUserById(userId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public ResponseEntity<UserDto> getUserById(String userId) {
        return ResponseEntity.ok(usersService.getUserById(userId));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public ResponseEntity<UserDto> updateUserById(String userId, NewUserDto newUserDto) {
        return ResponseEntity.ok(usersService.updateUserById(userId, newUserDto));
    }
}


