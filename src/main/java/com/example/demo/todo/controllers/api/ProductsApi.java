package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.NewProductDto;
import com.example.demo.todo.dto.ProductDto;
import com.example.demo.todo.dto.ProductPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Products")
})
@RequestMapping("/api/products")
public interface ProductsApi {
    @Operation(summary = "Get list of products", description = "accessible to all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with products",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductPage.class))
                    }
            )
    })
    @GetMapping
    ProductPage getAll();

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new product",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody NewProductDto newProduct);

    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete product by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") String productId);

    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update product by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable("id") String productId, @RequestBody NewProductDto newProduct);


    @Operation(summary = "Get product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get product by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDto.class))
                    }
            )
    })
    @GetMapping(value = "/{id}")
    ResponseEntity<ProductDto> getById(@PathVariable("id") String productId);

    @Operation(summary = "Get list of products by name", description = "accessible to all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with products",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductPage.class))
                    }
            )
    })

    @GetMapping(value = "/by/name/{name}")
    ProductPage nameFilter(@PathVariable String name);

    @Operation(summary = "Get list of products by category", description = "accessible to all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with products",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductPage.class))
                    }
            )
    })
    @GetMapping(value = "/by/category/{category}")
    ProductPage categoryFilter(@PathVariable String category);

    @Operation(summary = "Get list of products by price", description = "accessible to all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with products",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProductPage.class))
                    }
            )
    })
    @GetMapping(value = "/by/price")
    ProductPage priceFilter(@RequestParam(value = "startPrice") double startPrice, @RequestParam(value = "endPrice") double endPrice);
}
