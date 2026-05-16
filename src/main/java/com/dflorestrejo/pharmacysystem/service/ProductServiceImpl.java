package com.dflorestrejo.pharmacysystem.service;

import com.dflorestrejo.pharmacysystem.entity.Product;
import com.dflorestrejo.pharmacysystem.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product product) {

        if (product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }

        if (product.getMinimumStock() <= 0 ) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo");
        }

        if (product.getSalePrice().compareTo(product.getPurchasePrice()) < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser menor al de compra");
        }

        if (product.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vació");
        }

        if (product.getLaboratory().isBlank()) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vació");
        }

        if (product.getCurrentStock() < 0) {
            throw new IllegalArgumentException("El stock actual no puede ser negativo");
        }

        if (product.getPresentation().isBlank()) {
            throw new IllegalArgumentException("La presentación no puede estar vació");
        }

        if (product.getExpirationDate().getYear() < LocalDateTime.now().getYear()) {
            throw new IllegalArgumentException("La fecha de producto no puede ser menor a la fecha actual");
        }

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new RuntimeException("Error no tiene datos");
        }
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado con id: " + id);
        }
        productRepository.delete(id);
    }
}
