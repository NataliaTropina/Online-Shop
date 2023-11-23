package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.AddressDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
