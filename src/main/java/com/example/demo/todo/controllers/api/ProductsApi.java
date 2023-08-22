package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.dto.UsersPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tags(value = {
        @Tag(name = "Products")
})
@RequestMapping("/api/products")
public interface ProductsApi {
    @Operation(summary = "Получение списка товаров", description = "доступно всем")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с товарами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })
    @GetMapping
    List<ProductDto> getAll();

    @Operation(summary = "Создание нового товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "новый товар",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody NewProductDto newProduct);
}
