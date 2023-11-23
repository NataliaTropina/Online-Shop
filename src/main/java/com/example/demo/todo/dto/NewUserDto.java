package com.example.demo.todo.dto;


import com.example.demo.todo.models.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private String email;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String firstName;
    private String lastName;
    private String phone;
}