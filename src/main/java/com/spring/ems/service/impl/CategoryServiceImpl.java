package com.spring.ems.service.impl;

import com.spring.ems.entity.Category;
import com.spring.ems.repository.CategoryRepository;
import com.spring.ems.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
