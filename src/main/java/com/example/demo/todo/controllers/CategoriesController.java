package com.example.demo.todo.controllers;


import com.example.demo.todo.controllers.api.CategoriesApi;
import com.example.demo.todo.dto.CategoriesPage;
import com.example.demo.todo.dto.CategoryDto;
import com.example.demo.todo.dto.NewCategoryDto;
import com.example.demo.todo.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoriesController implements CategoriesApi {

    private final CategoriesService categoriesService;

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory (NewCategoryDto newCategory){
        return ResponseEntity
                .status(201)
                .body(categoriesService.createCategory(newCategory));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<CategoryDto> updateCategoryById(NewCategoryDto newCategoryDto, String id) {
        return ResponseEntity
                .status(201)
                .body(categoriesService.updateCategoryById(newCategoryDto, id));

    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoriesPage getAll() {
        return categoriesService.getAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<CategoryDto> getById(String id) {
        return ResponseEntity.ok(categoriesService.getById(id));
    }

    @Override
    public ResponseEntity<CategoryDto> deleteCategory(String id) {
        return ResponseEntity.ok(categoriesService.deleteCategory(id));
    }
}
