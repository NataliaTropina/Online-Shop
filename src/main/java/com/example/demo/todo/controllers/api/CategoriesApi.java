package com.example.demo.todo.controllers.api;

import com.example.demo.todo.dto.CategoriesPage;
import com.example.demo.todo.dto.CategoryDto;
import com.example.demo.todo.dto.NewCategoryDto;
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
        @Tag(name = "Categories")
})
@RequestMapping("/api/categories")
public interface CategoriesApi {


    @Operation(summary = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New category",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    }
            )
    })

    @PostMapping
    ResponseEntity<CategoryDto> createCategory(@RequestBody NewCategoryDto newCategory);

    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update category by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    }
            )
    })


    @PutMapping(value = "/{id}")
    ResponseEntity<CategoryDto> updateCategoryById(@RequestBody NewCategoryDto newCategoryDto, @PathVariable String id);

    @Operation(summary = "Access to all categories", description = "available to administrator and user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all categories",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoriesPage.class))
                    }
            )
    })

    @GetMapping
    CategoriesPage getAll();

    @Operation(summary = "Access to category by id", description = "available to administrator and user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "category by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    }
            )
    })


    @GetMapping(value = "/{id}")
    ResponseEntity<CategoryDto> getById(@PathVariable String id);

    @Operation(summary = "Delete category by id", description = "available only to administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete category by id",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryDto.class))
                    }
            )
    })

    @DeleteMapping(value = "/{id}")
    ResponseEntity<CategoryDto> deleteCategory(@PathVariable String id);
}
