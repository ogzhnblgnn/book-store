package com.example.backend.services.Abstract;

import java.util.List;

import com.example.backend.entities.Category;

public interface CategoryService {
    
    List<Category> getAllCategories();
    Category getCategoryById(int id);
    Category addCategory(Category category);
    Category updateCategory(int id, Category category);
    void deleteCategory(int id);

    
}
