package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Category;
import com.example.backend.entities.models.ApiResponse;

public interface CategoryService {
    
    ApiResponse<List<Category>> getAllCategories();
    ApiResponse<Category> getCategoryById(int id);
    ApiResponse<Category> addCategory(Category category);
    ApiResponse<Category> updateCategory(int id, Category category);
    ApiResponse<?> deleteCategory(int id);

    
}
