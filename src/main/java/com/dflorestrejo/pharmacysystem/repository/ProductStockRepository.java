package com.dflorestrejo.pharmacysystem.repository;

import com.dflorestrejo.pharmacysystem.entity.ProductStock;

import java.util.List;
import java.util.Optional;

public interface ProductStockRepository {

    ProductStock save (ProductStock productStock);

    Optional<ProductStock> findByProductAndBranch(int productId, int branchId);

    List<ProductStock> findByProduct(int productId);

    int totalStockProduct(int productId);

    void updateQuantity(int productId,int branchId, int quantity);
}
