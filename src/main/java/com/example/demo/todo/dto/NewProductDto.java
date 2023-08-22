package com.example.demo.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProductDto {

    private String name;
    private String description;
    private String imageURL;
    private String country;
    private String categoryId;
    private double price;
    private int quantity;
}
