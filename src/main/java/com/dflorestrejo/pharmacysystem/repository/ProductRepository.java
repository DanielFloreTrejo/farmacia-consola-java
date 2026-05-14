package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(int id);

    List<Product> findAll();

    Optional<Product> findByName(String name);

    void update(Product product);

    void delete(int id);
}
