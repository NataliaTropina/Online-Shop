package com.example.demo.todo.dto;

import com.example.demo.todo.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String imageURL;
    private String country;
    private String categoryId;
    private double price;
    private int quantity;


    public static ProductDto from (Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageURL(product.getImageURL())
                .country(product.getCountry())
                .categoryId(product.getCategory().getId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }


    public static List<ProductDto> from (List<Product> products){
        return products.stream().map(ProductDto::from).collect(Collectors.toList());
    }
}
