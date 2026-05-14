package com.dflorestrejo.pharmacysystem.controller;

import com.dflorestrejo.pharmacysystem.service.ProductService;

import java.util.Scanner;

public class ProductController {

    private final ProductService productService;
    private final Scanner scanner;

    public ProductController(ProductService productService, Scanner scanner) {
        this.productService = productService;
        this.scanner = scanner;
    }


}
