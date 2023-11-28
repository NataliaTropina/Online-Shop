package com.example.demo.todo.dto;

import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.Cart;
import com.example.demo.todo.models.Order;
import com.example.demo.todo.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Зарегистрированный пользователь")
public class UserDto {
    @Schema(description = "идентификатор пользователя", example = "1")
    private String id;
    private String firstName;
    private String lastName;
    @Schema(description = "имя пользователя", example = "username")
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
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .role(user.getRole())
                .cartId(user.getCart().getId())
                .addressIds(user.getAddresses().stream().map(Address::getId).collect(Collectors.toList()))
                .orderIds(user.getOrders().stream().map(Order::getId).collect(Collectors.toList()))
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}

