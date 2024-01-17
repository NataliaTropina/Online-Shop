package com.example.demo.todo.services;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.dto.ProductPage;

public interface ProductService {

    ProductPage getAll();

    ProductDto createProduct(NewProductDto newProduct);

    ProductDto deleteProduct(String productId);

    ProductDto updateProduct(String productId, NewProductDto newProduct);

    ProductDto getById(String productId);

    ProductPage nameFilter(String name);

    ProductPage categoryFilter(String category);

    ProductPage priceFilter(double startPrice, double endPrice);
}
