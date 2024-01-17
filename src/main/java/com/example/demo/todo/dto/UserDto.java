package com.example.demo.todo.dto;

import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String phone;
    private User.Role role;
    private String cartId;
    private List<String> addressIds;
    private List<String> orderIds;

    public static UserDto from(User user) {

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .addressIds(user.getAddresses().stream().map(Address::getId).collect(Collectors.toList()))
                .cartId(user.getCart().getId())
                .orderIds(user.getOrders().stream().map(Order::getId).collect(Collectors.toList()))
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}

