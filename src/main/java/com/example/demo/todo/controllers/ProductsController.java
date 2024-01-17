package com.example.demo.todo.controllers;

import com.example.demo.todo.controllers.api.ProductsApi;
import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.dto.ProductPage;
import com.example.demo.todo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductsController implements ProductsApi {

    public final ProductService productService;

    public ProductPage getAll() {
        return productService.getAll();
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(NewProductDto newProduct) {
        return ResponseEntity
                .status(201)
                .body(productService.createProduct(newProduct));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> deleteProduct(String productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(String productId, NewProductDto newProduct) {
        return ResponseEntity.ok(productService.updateProduct(productId, newProduct));
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductDto> getById(String productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @Override
    public ProductPage nameFilter(String name) {
        return productService.nameFilter(name);
    }

    @Override
    public ProductPage categoryFilter(String category) {
        return productService.categoryFilter(category);
    }

    @Override
    public ProductPage priceFilter(double startPrice, double endPrice) {
        return productService.priceFilter(startPrice, endPrice);
    }
}
