package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                    schema = @Schema(implementation = ProductPage.class))
                    }
            )
    })
    @GetMapping
    ProductPage getAll();

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

    @Operation(summary = "Удаление товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Удаление товара по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<ProductDto> deleteProduct(@PathVariable ("id") String productId);

    @Operation(summary = "Обновление товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Обновление товара по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable ("id") String productId, @RequestBody NewProductDto newProduct);


    @Operation(summary = "Получение товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Получение товара по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })
    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDto> getById(@PathVariable ("id") String productId);

}
