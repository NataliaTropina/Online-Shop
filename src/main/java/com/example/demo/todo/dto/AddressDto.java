package com.example.demo.todo.dto;

import com.example.demo.todo.models.Address;
import com.example.demo.todo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private String id;
    private String street;
    private String houseNumber;
    private String city;
    private String Country;
    private String postcode;
    private String userId;


    public static AddressDto from (Address address) {

        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .Country(address.getCountry())
                .postcode(address.getPostcode())
                .userId(address.getUser().getId())
                .build();
    }

    public static List<AddressDto> from (List<Address> addresses){

        return addresses.stream().map(AddressDto::from).collect(Collectors.toList());
    }

}
