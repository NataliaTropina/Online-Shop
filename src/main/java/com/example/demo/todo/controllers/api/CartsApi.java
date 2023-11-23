package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.CartDto;
import com.example.demo.todo.dto.CategoryDto;
import com.example.demo.todo.dto.NewCartDto;
import com.example.demo.todo.dto.NewCategoryDto;
import com.example.demo.todo.security.datails.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tags(value = {
        @Tag(name = "Cart")
})
@RequestMapping("/api/cart")
public interface CartsApi {

    @Operation(summary = "Create new cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "add products to cart",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<CartDto> addToCart (@Parameter(hidden = true)
                                       @AuthenticationPrincipal AuthenticatedUser currentUser,
                                       @RequestBody NewCartDto newCart);
}
