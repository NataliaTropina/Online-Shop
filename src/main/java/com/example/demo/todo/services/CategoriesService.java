package com.example.demo.todo.services;

import com.example.demo.todo.dto.CategoriesPage;
import com.example.demo.todo.dto.CategoryDto;
import com.example.demo.todo.dto.NewCategoryDto;

public interface CategoriesService {

    CategoryDto createCategory(NewCategoryDto newCategory);

    CategoryDto updateCategoryById(NewCategoryDto newCategory, String id);

    CategoriesPage getAll();

    CategoryDto getById(String id);

    CategoryDto deleteCategory(String id);
}
