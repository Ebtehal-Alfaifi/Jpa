package com.example.ejpa.Controller;

import com.example.ejpa.ApiResponse.Api;
import com.example.ejpa.Model.Category;
import com.example.ejpa.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(categoryService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category , Errors errors) {
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCate(category);
        return ResponseEntity.status(200).body(new Api(" add success"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category,Errors errors) {
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        Boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Category updated successfully");
        }
        return ResponseEntity.status(200).body(" category not found");
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        Boolean isDeleted = categoryService.deleteCategory(id);
        if (!isDeleted) {
            return ResponseEntity.status(404).body("Category not found");
        }
        return ResponseEntity.status(200).body("Category deleted successfully");
    }



}
