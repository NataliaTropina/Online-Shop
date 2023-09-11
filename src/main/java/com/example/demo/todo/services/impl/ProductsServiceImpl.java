package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.dto.ProductPage;
import com.example.demo.todo.exceptions.NotFoundException;
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
    public ProductPage getAll() {
       List<Product> products =  productsRepository.findAll();

         return ProductPage.builder().data(ProductDto.from(products)).build();
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

    @Override
    public ProductDto deleteProduct(String productId) {

        Product product = productsRepository.findById(productId)
                        .orElseThrow(()->
                                new NotFoundException("Product with id <" + productId + "> not found")
                                );

        productsRepository.delete(product);

        return ProductDto.from(product);
    }

    @Override
    public ProductDto updateProduct(String productId, NewProductDto newProduct) {

        Product product = productsRepository.findById(productId)
                        .orElseThrow(()->
                                new NotFoundException("Product with id <" + productId + "> not found"));

        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setCountry(newProduct.getCountry());
        product.setImageURL(newProduct.getImageURL());
        product.setCategoryId(newProduct.getCategoryId());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());

        productsRepository.save(product);


        return ProductDto.from(product);
    }

    @Override
    public ProductDto getById(String productId) {

        Product product = productsRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product with id <" + productId + "> not found"));

        return ProductDto.from(product);
    }
}
