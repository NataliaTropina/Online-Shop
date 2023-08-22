package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.models.Product;
import com.example.demo.todo.repositories.ProductsRepository;
import com.example.demo.todo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    public List<ProductDto> getAll() {
       List<Product> products =  productsRepository.findAll();

         return ProductDto.from(products);
    }

    @Override
    public ProductDto createProduct(NewProductDto newProduct) {
        System.out.println("Received JSON: " + newProduct);
        Product product = Product.builder()
                .name(newProduct.getName())
                .description(newProduct.getDescription())
                .country(newProduct.getCountry())
                .imageURL(newProduct.getImageURL())
                .categoryId(newProduct.getCategoryId())
                .price(newProduct.getPrice())
                .quantity(newProduct.getQuantity())
                .build();
        productsRepository.save(product);

        return ProductDto.from(product);
    }
}
