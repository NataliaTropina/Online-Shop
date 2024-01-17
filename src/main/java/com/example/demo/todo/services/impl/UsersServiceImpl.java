package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.ProfileDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.dto.UsersPage;
import com.example.demo.todo.exceptions.IncorrectDeleteException;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.User;
import com.example.demo.todo.repositories.UsersRepository;
import com.example.demo.todo.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Override
    public ProfileDto getProfile(String currentUserId) {
        User user = usersRepository.findById(currentUserId)
                .orElseThrow(IllegalArgumentException::new);

        return ProfileDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
    @Override
    public UsersPage getAll(String role) {
        List<User> users = usersRepository.findAllByRole(role);
        return UsersPage.builder()
                .data(UserDto.from(users))
                .build();

    }

    @Override
    public UserDto deleteUserById(String userId) {

        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found")
                );
        if (user.getRole() == User.Role.ADMIN) {
            throw new IncorrectDeleteException("Admin can not be deleted");
        }
        usersRepository.deleteById(userId);
        return UserDto.from(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found"));

        return UserDto.from(user);
    }

    @Override
    public UserDto updateUserById(String userId, NewUserDto newUserDto) {

        User user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("user with id <" + userId + "> not found"));

        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setUpdatedDate(newUserDto.getUpdateDate());
        user.setEmail(newUserDto.getEmail());
        user.setPhone(newUserDto.getPhone());

        usersRepository.save(user);

        return UserDto.from(user);
    }
}
