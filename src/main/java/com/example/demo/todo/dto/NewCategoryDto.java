package com.example.demo.todo.dto;

import com.example.demo.todo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCategoryDto {

    private String name;
    private String description;
    private List<String> productIds;
}
