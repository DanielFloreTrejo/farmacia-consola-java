package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.entity.ProductStock;

import java.util.List;
import java.util.Optional;

public interface ProductStockRepository {

    ProductStock save (ProductStock productStock);

    Optional<ProductStock> findById(int id);

    Optional<ProductStock> findByName(String name);

    List<ProductStock> findAll();

    void update(ProductStock productStock);
}
