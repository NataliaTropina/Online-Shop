package com.example.demo.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewAddressDto {

    private String street;
    private String houseNumber;
    private String city;
    private String Country;
    private String postcode;
}
