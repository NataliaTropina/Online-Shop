package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.NewUserDto;
import com.example.demo.todo.dto.ProfileDto;
import com.example.demo.todo.dto.UserDto;
import com.example.demo.todo.dto.UsersPage;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {

    @Operation(summary = "Получение своего профиля", description = "Доступно только аутентифицированному пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информацию о профиле",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProfileDto.class))
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Пользователь не аутентифицирован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            )
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my/profile")
    ResponseEntity<ProfileDto> getProfile(@Parameter(hidden = true)
                                          @AuthenticationPrincipal AuthenticatedUser currentUser);


    @Operation(summary = "Получение списка пользователей", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с пользователями",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsersPage.class))
                    }
            )
    })

    @GetMapping(value = "")
    ResponseEntity<UsersPage> getAll();


    @Operation(summary = "Удаление пользователя по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удаление пользователя по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<UserDto> deleteUserById(@PathVariable("id") String userId);

    @Operation(summary = "Получение пользователя по ID", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            )
    })
    @GetMapping(value = "/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") String userId);

    @Operation(summary = "Обновление данных пользователя и сохранение в базу данных", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление пользователя по ID",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Пользователь не аутентифицирован",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "StandardResponseDto"))
                    }
            )
    })
    @PreAuthorize("isAuthenticated()")
    @PutMapping(value = "/{id}")
    ResponseEntity<UserDto> updateUserById(@PathVariable("id") String userId, @RequestBody NewUserDto newUserDto);

}
