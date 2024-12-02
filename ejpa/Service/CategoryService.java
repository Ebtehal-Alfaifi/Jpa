package com.example.ejpa.Service;

import com.example.ejpa.Model.Category;
import com.example.ejpa.Repostry.CategoryRepostry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepostry categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void addCate(Category category) {
        categoryRepository.save(category);
    }

    public Boolean updateCategory(Integer id, Category category) {
        Category old = categoryRepository.getById(id);
        if (old == null) {
            return false;
        }
        old.setName(category.getName());
        categoryRepository.save(old);
        return true;
    }

    public Boolean deleteCategory(Integer id) {
        Category old = categoryRepository.getById(id);
        if (old == null) {
            return false;
        }
        categoryRepository.delete(old);
        return true;
    }

}
