package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save (Category category);

    List<Category> findAll();

    Optional<Category> findById(int id);

    Optional<Category> findByName(String name);

    void update(Category category);

    void delete (int id);
}
