package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;

import java.util.List;

public interface ProductService  {

    List<ProductDto> getAll();

    ProductDto createProduct (NewProductDto newProduct);
}
