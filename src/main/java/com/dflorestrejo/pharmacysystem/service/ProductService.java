package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product add(Product product);

    List<Product> findAll();

    Optional<Product> findById(int id);

    void update(Product product);

    void delete(int id);
}
