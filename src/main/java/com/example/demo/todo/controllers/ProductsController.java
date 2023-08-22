package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.ProductsApi;
import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    public final ProductService productService;
    public List<ProductDto> getAll(){
    return productService.getAll();
    }

    @Override
    public ResponseEntity<ProductDto> createProduct(NewProductDto newProduct) {
        return ResponseEntity
                .status(201)
                .body(productService.createProduct(newProduct));
    }

}
