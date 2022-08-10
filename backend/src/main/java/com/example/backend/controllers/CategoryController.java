package com.example.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entities.Category;
import com.example.backend.entities.models.ApiResponse;
import com.example.backend.services.Abstract.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService; // IOC, DI

    public CategoryController(CategoryService categoryService) { // CONSTRUCTOR INJECTION
        this.categoryService = categoryService;
    }

    @GetMapping
    public ApiResponse<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ApiResponse<Category> getCategoryById(@PathVariable(name = "id", required = true) int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ApiResponse<Category> addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    public ApiResponse<Category> updateCategory(@PathVariable(name = "id", required = true) int id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteCategory(@PathVariable(name = "id", required = true) int id) {
        return categoryService.deleteCategory(id);
    }
}
