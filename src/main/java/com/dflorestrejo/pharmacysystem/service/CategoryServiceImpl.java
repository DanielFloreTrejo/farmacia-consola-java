package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.Category;
import com.dflorestrejo.pharmacysystem.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {

        if (category.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vació");
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(int id) {

    }
}
