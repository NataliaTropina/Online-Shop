package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "addresses")
@ToString
public class Address {

    @Id
    private String id;
    private String street;
    private String houseNumber;
    private String city;
    private String Country;
    private String postcode;
    @DBRef
    private User user;
}
