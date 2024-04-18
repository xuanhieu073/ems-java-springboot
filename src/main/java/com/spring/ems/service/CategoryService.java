package com.spring.ems.service;

import com.spring.ems.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAll();
}
