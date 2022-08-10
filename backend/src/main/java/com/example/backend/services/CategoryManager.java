package com.example.backend.services;

import java.util.List;

import com.example.backend.entities.Category;
import com.example.backend.repositories.CategoryRepository;
import com.example.backend.services.Abstract.CategoryService;

public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Category could not found!"));
    }

    @Override
    public Category addCategory(Category category) {
        // TODO
        return null;
    }

    @Override
    public Category updateCategory(int id, Category category) {
        // TODO
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        // TODO

    }

}
