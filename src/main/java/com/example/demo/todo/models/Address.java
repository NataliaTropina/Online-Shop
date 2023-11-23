package com.example.demo.todo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Address {

    @Id
    private String id;
    private String street;
    private String houseNumber;
    private String city;
    private String Country;
    private String postcode;
    private User user;
}
