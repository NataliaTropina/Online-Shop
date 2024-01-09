package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.AddressDto;
import com.example.demo.todo.dto.AddressesPage;
import com.example.demo.todo.dto.NewAddressDto;
import com.example.demo.todo.dto.OrderDto;
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

@Tags(value = {
        @Tag(name = "Address")
})
@RequestMapping("/api/address")
public interface AddressesApi {

    @Operation(summary = "Create new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New address",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<AddressDto> createAddress (@RequestBody NewAddressDto newAddress,
                                              @Parameter(hidden = true)
                                              @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Update  address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update address by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressDto.class))
                    }
            )
    })

    @PutMapping(value = "/{id}")
    ResponseEntity<AddressDto> updateAddress (@RequestBody NewAddressDto newAddress,
                                              @PathVariable String id);

    @Operation(summary = "Delete  address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete address by ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressDto.class))
                    }
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<AddressDto> deleteAddress (@PathVariable("id") String id,
                                              @Parameter(hidden = true)
                                              @AuthenticationPrincipal AuthenticatedUser currentUser);

    @Operation(summary = "Get addresses by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get all addresses by user",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddressDto.class))
                    }
            )
    })
    @GetMapping("/by-user")
    AddressesPage getAddressesByUser (@Parameter(hidden = true)
                                         @AuthenticationPrincipal AuthenticatedUser currentUser);

}
