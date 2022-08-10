package com.example.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entities.Category;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.repositories.CategoryRepository;
import com.example.backend.services.Abstract.CategoryService;

@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ApiResponse.default_OK(categories);
    }

    @Override
    public ApiResponse<Category> getCategoryById(int id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category could not found!"));

        return ApiResponse.default_OK(category);
    }

    @Override
    public ApiResponse<Category> addCategory(Category category) {
        Category addCategory = categoryRepository.save(category);
        return ApiResponse.default_CREATED(addCategory);
    }

    @Override
    public ApiResponse<Category> updateCategory(int id, Category category) {
        getCategoryById(id);
        category.setId(id);
        Category updateCategory = categoryRepository.save(category);
        return ApiResponse.default_ACCEPTED(updateCategory);
    }

    @Override
    public ApiResponse<?> deleteCategory(int id) {
        categoryRepository.deleteById(id);
        return ApiResponse.default_NO_CONTENT();
    }

}
