package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = {
        @Tag(name = "Orders")
})
@RequestMapping("/api/orders")
public interface OrdersApi {

    @Operation(summary = "Create new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New order",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<OrderDto> createOrder (@Parameter(hidden = true)
                                          @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Получение списка всех заказов", description = "заказов доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с заказами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrdersPage.class))
                    }
            )
    })

    @GetMapping
    OrdersPage getAll ();

    @Operation(summary = "Получение заказа по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "заказ по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    }
            )
    })

    @GetMapping(value = "/{id}")
    OrderDto getById (@PathVariable("id") String id);

    @Operation(summary = "Обновление заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Обновление заказа по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<OrderDto> updateOrder (@PathVariable("id") String id,
                                          @Parameter(hidden = true)
                                          @AuthenticationPrincipal AuthenticatedUser currentUser,
                                          @RequestBody NewOrderDto newOrder);


    @Operation(summary = "Удаление заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление заказа по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    }
            )
    })

    @DeleteMapping(value = ("/{id}"))
    ResponseEntity<OrderDto> deleteOrderById (@PathVariable("id") String id);
}
