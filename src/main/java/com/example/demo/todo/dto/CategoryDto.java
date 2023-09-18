package com.example.demo.todo.dto;

import com.example.demo.todo.models.Category;
import com.example.demo.todo.models.Product;
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
public class CategoryDto {

    private String id;
    private String name;
    private String description;
    private List<String> productIds;

    public static CategoryDto from (Category category){

        return CategoryDto
                .builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .productIds(category.getProducts().stream().map(Product::getId).collect(Collectors.toList()))
                .build();
    }

    public static List<CategoryDto> from (List<Category> categories){

        return categories.stream().map(CategoryDto::from).collect(Collectors.toList());
    }

}
