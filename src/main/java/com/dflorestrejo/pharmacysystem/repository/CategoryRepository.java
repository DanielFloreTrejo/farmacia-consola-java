package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save (Category category);

    Optional<Category> findById(int id);

    Optional<Category> findByName(String name);

    List<Category> findAll();

    void update(Category category);

    void delete(int id);
}
