package com.example.demo.todo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(getId(), address.getId()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getHouseNumber(), address.getHouseNumber()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getPostcode(), address.getPostcode()) && Objects.equals(getUser(), address.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreet(), getHouseNumber(), getCity(), getCountry(), getPostcode(), getUser());
    }
}
