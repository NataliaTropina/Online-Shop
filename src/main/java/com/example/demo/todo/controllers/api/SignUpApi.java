package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/api/register")
public interface SignUpApi {

    @Operation(summary = "User registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registered user",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<UserDto> signUp(@RequestBody NewUserDto newUser);
}
