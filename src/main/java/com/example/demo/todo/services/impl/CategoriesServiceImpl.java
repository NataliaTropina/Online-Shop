package com.example.demo.todo.services.impl;

import com.example.demo.todo.dto.CategoriesPage;
import com.example.demo.todo.dto.CategoryDto;
import com.example.demo.todo.dto.NewCategoryDto;
import com.example.demo.todo.exceptions.NotFoundException;
import com.example.demo.todo.models.Category;
import com.example.demo.todo.models.Product;
import com.example.demo.todo.repositories.CategoriesRepository;
import com.example.demo.todo.repositories.ProductsRepository;
import com.example.demo.todo.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final ProductsRepository productsRepository;
    @Override
    public CategoryDto createCategory(NewCategoryDto newCategory) {

        List<Product> products = productsRepository.findAllByIdIn(newCategory.getProductIds());

        Category category = Category.builder()
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .products(products)
                .build();

        categoriesRepository.save(category);

        return CategoryDto.from(category);
    }

    @Override
    public CategoryDto updateCategoryById(NewCategoryDto newCategory, String id) {

        Category category = categoriesRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Category with id <" + id + "> not found")
                        );

        List<Product> products = productsRepository.findAllByIdIn(newCategory.getProductIds());

        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
        category.setProducts(products);

        categoriesRepository.save(category);

        return CategoryDto.from(category);
    }

    @Override
    public CategoriesPage getAll() {

        List<Category> categoryList = categoriesRepository.findAll();

        return CategoriesPage.builder().data(CategoryDto.from(categoryList)).build();
    }

    @Override
    public CategoryDto getById(String id) {

        Category category = categoriesRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Category with id <" + id + "> not found")
                        );

        return CategoryDto.from(category);
    }

    @Override
    public CategoryDto deleteCategory(String id) {


        Category category = categoriesRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Category with id <" + id + "> not found")
                );

        categoriesRepository.delete(category);

        return CategoryDto.from(category);

    }
}
